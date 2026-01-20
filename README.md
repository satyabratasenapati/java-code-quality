```markdown
# Java Pre-Commit Code Quality Enforcer

A lightweight Git pre-commit hook that automatically validates staged Java files against quality and security rules defined in Markdown files. If violations are found, the commit is blocked and you get actionable messages so you can fix issues before they reach the repo.

---

## How it works

- A pre-commit hook calls the Python script `scripts/validate_java.py`.
- The script scans staged Java files and checks them against the rules in `rules/*.md`.
- If a violation is detected (e.g., `System.out.println`, hardcoded credentials), the commit is blocked and the script prints errors.
- If all checks pass, the commit proceeds.

---

## Project structure (actual files in this repository)

This README reflects the current repository layout and example/test files that are included here.

```text
java-code-quality/
├── BadAnnotations.java         # Example file containing annotation violations
├── BadConfiguration.java       # Example file containing hardcoded config violations
├── BadQuality.java             # Example file containing general quality violations
├── GoodCode.java               # Example file demonstrating compliant code
├── rules/                      # Place your Markdown rule files here
│   └── (e.g., java_annotations.md, java_code_quality.md, java_hardcoded_values.md)
├── scripts/                    # Validation logic (Python)
│   └── validate_java.py        # The validator script (hook target)
└── README.md                   # This documentation
```

Quick links to files in this repo:
- Bad annotations example: [BadAnnotations.java](https://github.com/satyabratasenapati/java-code-quality/blob/master/BadAnnotations.java)
- Bad configuration example: [BadConfiguration.java](https://github.com/satyabratasenapati/java-code-quality/blob/master/BadConfiguration.java)
- Bad quality example: [BadQuality.java](https://github.com/satyabratasenapati/java-code-quality/blob/master/BadQuality.java)
- Good code example: [GoodCode.java](https://github.com/satyabratasenapati/java-code-quality/blob/master/GoodCode.java)
- Rules folder: [rules/](https://github.com/satyabratasenapati/java-code-quality/tree/master/rules)
- Scripts folder: [scripts/](https://github.com/satyabratasenapati/java-code-quality/tree/master/scripts)

---

## Prerequisites

Ensure the following (add or adjust as needed for your repo):

- `scripts/validate_java.py` — the validation script (make sure it exists and is executable)
- `rules/*.md` — one or more rule files, for example:
  - `rules/java_annotations.md`
  - `rules/java_code_quality.md`
  - `rules/java_hardcoded_values.md`
- `.git/hooks/pre-commit` — hook that calls the script (make executable with `chmod +x .git/hooks/pre-commit`)

---

## Standard Operating Procedure (SOP)

Follow these phases to verify and use the quality gate.

### Phase 1 — Verify Rule Rejection (Bad code)

1. Create or use the provided test files with known violations:
   - `BadAnnotations.java`
   - `BadConfiguration.java`
   - `BadQuality.java`

2. Stage and attempt to commit:

```bash
git add BadAnnotations.java BadConfiguration.java BadQuality.java
git commit -m "Test: Verifying hook rejection"
```

Expected output (example):

```
🚫 COMMIT REJECTED: Please fix the issues above.
 - BadAnnotations.java: Forbidden annotation @Ignore found
 - BadConfiguration.java: Hardcoded password detected in variable PASSWORD
 - BadQuality.java: Use of System.out.println detected; use a logger
```

The commit should be blocked until you fix the reported issues.

---

### Phase 2 — Committing Clean Code (Good code)

1. Fix or remove the bad files and create a compliant file, e.g., `GoodCode.java` (uses loggers, JUnit 5, externalized configuration).

2. Stage and commit the good file:

```bash
git reset
rm BadAnnotations.java BadConfiguration.java BadQuality.java
git add GoodCode.java
git commit -m "Feat: Add compliant Java module"
```

Expected output:

```
✅ All checks passed.
[main 1234567] Feat: Add compliant Java module
```

---

### Phase 3 — Pushing to remote

```bash
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPO.git
git branch -M main
git push -u origin main
```

---

## Emergency bypass (use sparingly)

To bypass checks in an absolute emergency (this skips all validation):

```bash
git commit -m "Emergency fix" --no-verify
```

Warning: This should only be used for critical hotfixes.

---

## Tips & troubleshooting

- Ensure `.git/hooks/pre-commit` is executable: `chmod +x .git/hooks/pre-commit`
- Run the validator manually to debug: `python3 scripts/validate_java.py --staged` (or check script usage)
- Keep your `rules/*.md` files up to date with the coding standards you want enforced.

---
```
