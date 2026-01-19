#!/usr/bin/env python3
import sys
import re
import subprocess
import os
import glob

# Configuration
RULES_DIR = 'rules'  # Folder where your .md files live
TARGET_EXTENSIONS = ('.java',)

def load_all_rules(rules_folder):
    """
    Scans the specific folder for ALL .md files and extracts rules.
    Returns a list of (Pattern, Message).
    """
    all_patterns = []
    
    # Find all .md files in the rules directory
    search_path = os.path.join(rules_folder, "*.md")
    rule_files = glob.glob(search_path)
    
    if not rule_files:
        print(f"⚠️  No rule files found in '{rules_folder}/'.")
        return []

    print(f"📂 Loading rules from: {[os.path.basename(f) for f in rule_files]}")

    for file_path in rule_files:
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                for line in f:
                    # Regex to find table rows: | `pattern` | message |
                    match = re.search(r'\|\s*`(.*?)`\s*\|\s*(.*?)\s*\|', line)
                    if match:
                        pattern = match.group(1)
                        message = match.group(2)
                        all_patterns.append((pattern, message))
        except Exception as e:
            print(f"❌ Error reading {file_path}: {e}")

    return all_patterns

def get_staged_java_files():
    """Returns a list of .java filenames that are staged for commit."""
    try:
        # Get list of files that are staged (Added, Copied, Modified)
        result = subprocess.run(
            ['git', 'diff', '--cached', '--name-only', '--diff-filter=ACM'],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True,
            check=True
        )
        files = result.stdout.splitlines()
        # Filter for only Java files right here
        return [f for f in files if f.endswith(TARGET_EXTENSIONS)]
    except subprocess.CalledProcessError:
        print("Error: Could not retrieve staged files.")
        sys.exit(1)

def check_file_content(filename, forbidden_patterns):
    errors_found = False
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            lines = f.readlines()
            
        for i, line in enumerate(lines):
            for pattern, message in forbidden_patterns:
                if re.search(pattern, line):
                    print(f"\n❌ Violation in {filename}:{i + 1}")
                    print(f"   Reason: {message}")
                    print(f"   Code:   {line.strip()}")
                    errors_found = True
    except (UnicodeDecodeError, FileNotFoundError):
        pass 

    return errors_found

def main():
    # 1. Load rules from ALL md files
    forbidden_patterns = load_all_rules(RULES_DIR)
    
    if not forbidden_patterns:
        print("✅ No rules found. Allowing commit.")
        sys.exit(0)

    # 2. Get staged Java files
    staged_files = get_staged_java_files()
    
    if not staged_files:
        # No java files changed, so we don't care
        sys.exit(0)

    print(f"🔍 Checking {len(staged_files)} Java file(s) against {len(forbidden_patterns)} rules...")

    # 3. Check content
    found_errors = False
    for file in staged_files:
        if check_file_content(file, forbidden_patterns):
            found_errors = True

    if found_errors:
        print("\n🚫 COMMIT REJECTED: Please fix the issues above.")
        sys.exit(1)
    else:
        print("✅ All checks passed.")
        sys.exit(0)

if __name__ == "__main__":
    main()