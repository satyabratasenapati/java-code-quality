Java Pre-Commit Code Quality Enforcer
This project uses a custom Git Pre-Commit Hook to automatically validate Java code against a set of quality rules defined in Markdown files.

How it works: When you run git commit, a Python script scans your staged Java files. It checks them against rules defined in rules/*.md.

If Bad Code (e.g., System.out.println, hardcoded passwords) is found -> Commit is Blocked.

If Good Code is found -> Commit Succeeds.

📂 1. Project Structure
Your project should be organized like this:

Plaintext

my-project/
├── .git/
│   └── hooks/
│       └── pre-commit        # The trigger (calls the python script)
├── rules/                    # FOLDER: Place Markdown rule files here
│   ├── java_quality.md       
│   └── java_security.md
├── scripts/
│   └── validate_java.py      # The logic script
├── src/                      # Your source code
└── README.md                 # This documentation

📂** Prerequisites**
Ensure the following files are already present in your repository:

scripts/validate_java.py (The logic script)

rules/java_annotations.md (Rules for annotations)

rules/java_code_quality.md (Rules for general quality)

rules/java_hardcoded_values.md (Rules for security)

🧪 Standard Operating Procedure (SOP)
Follow these steps to verify that the quality gate is working correctly.

Phase 1: Verify Rule Rejection (The "Bad" Code)
We will attempt to commit three files containing known violations to ensure the hook blocks them.

1. Create the Test Files Create the following three files in your project root (content not shown here, use standard violations like System.out.println, Thread.sleep, or @Ignore):

BadAnnotations.java

BadConfiguration.java

BadQuality.java

2. Run Git Commands Run the following commands in your terminal (PowerShell or Bash):

Bash

# 1. Stage the bad files
git add BadAnnotations.java BadConfiguration.java BadQuality.java

# 2. Attempt to commit (THIS MUST FAIL)
git commit -m "Test: Verifying hook rejection"
Expected Output:

🚫 COMMIT REJECTED: Please fix the issues above. (You should see specific error messages for all three files).

Phase 2: Committing Clean Code (The "Good" Code)
Now we will clean up the bad files and commit a file that adheres to all rules.

1. Clean Up & Create Good File Delete the bad files and create GoodCode.java (which uses Loggers, JUnit 5, and configuration properties).

2. Run Git Commands

Bash

# 1. Unstage the failed files (if needed) and remove them
git reset
rm BadAnnotations.java, BadConfiguration.java, BadQuality.java  # PowerShell syntax

# 2. Stage the valid file
git add GoodCode.java

# 3. Commit (THIS MUST SUCCEED)
git commit -m "Feat: Add compliant Java module"
Expected Output:

✅ All checks passed. [main 1234567] Feat: Add compliant Java module

Phase 3: Pushing to Remote
Once your clean code is committed locally, push it to the server.

Bash

# 1. Link to remote (Only do this once if not already linked)
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPO.git

# 2. Rename branch to main (Standard practice)
git branch -M main

# 3. Push code
git push -u origin main
🚨 **Emergency Bypass Process**
Critical Warning: This process bypasses ALL quality checks. Use this strictly for emergency hotfixes where the validation script is blocking a critical release.

To skip the checks, add the --no-verify flag:

git commit -m "Emergency fix" --no-verify
