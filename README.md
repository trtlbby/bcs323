# bcs323 🧑‍💻

This repository contains my academic and class activities for BCS 323. It serves as a log of my solutions to various assignments, exercises, and projects related to information security.

## Purpose

- **Personal review:** I use this repo to keep track of my progress and revisit my work for future reference.
- **Peer review:** Sharing my solutions enables others in the class to review, learn from, and discuss different approaches to information security.
- **Documentation:** All activities, solutions, and relevant code are organized for easy access and understanding.

## Language

Not set to one (Currently using Java).

## Usage

Feel free to browse, learn, and contribute feedback or suggestions. This repository is intended for class collaboration and academic growth.

## ACT#1-BACSAIN — Security Improvements

### Project layout (readability)
`ACT#1-BACSAIN` was reorganized to be easier to browse:
- `ACT#1-BACSAIN\src\` — Java source files
- `ACT#1-BACSAIN\lib\` — dependencies (e.g., MySQL connector ZIP)

### Role-Based Access Control (RBAC)
- On startup, the app asks for a **role**.
- Only **ADMIN** can open the Employee Entry and User Entry modules.
- Admin access code is read from environment variable: `ACT1_ADMIN_CODE`
  - If not set, a default code is used: `admin` (shown as a warning).

### Password Security (salting + “information coating”)
- Passwords are **NOT** stored as plain text.
- Passwords are hashed using **PBKDF2WithHmacSHA256** with a **per-password random salt**.
- Stored password format (single DB column):
  - `PBKDF2$sha256$<iterations>$<salt_b64>$<hash_b64>`
- This “coating” stores the algorithm + parameters with the hash so it’s verifiable and upgrade-friendly.

### Database configuration
The database connection uses environment variables:
- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`

## License

This repository is intended for educational purposes.
