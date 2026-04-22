# Divvvi — Build Plan

**Product:** Divvvi — "Scan it. Adjust it. Done."
**Source PRD:** MVP → Phase 2 Structured
**Target platform:** Native Android (Kotlin + Jetpack Compose)
**Builder:** Solo (product designer)

---

## 1. Product Understanding

Divvvi is the fastest way to go from a receipt → a fair split among roommates (max 5 per group). The OCR scan + review/edit loop is the core experience; the per-person math is computed server-side but intentionally hidden in the MVP UI. Phase 2 introduces "who owes whom," payer selection, and a balance dashboard.

**Critical success metric:** time from scan → confirm < 2 minutes.

---

## 2. Stack (all free tiers)

| Layer | Choice | Why |
|---|---|---|
| Language / UI | Kotlin + Jetpack Compose | Modern Android default; declarative style maps well to design tools |
| Architecture | MVVM + Repository | Standard, well-documented |
| Min SDK / Target | 26 / 35 | Broad device coverage |
| Backend | Firebase (Auth + Firestore) | Free Spark plan; no server code. Storage skipped — it now requires the paid Blaze plan to enable |
| Auth | Firebase Anonymous Auth + display name | "Quick signup" with zero friction |
| OCR | **Google ML Kit Text Recognition (on-device)** | 100% free, unlimited, no API key, no quota — runs locally on the phone |
| Camera | CameraX | Google's official camera library |
| Image loading | Coil | Compose-native |
| Navigation | Navigation Compose | Built-in |
| Permissions | Accompanist Permissions | Clean Compose API |

**Note on OCR choice:** The PRD suggests Google Vision API or AWS Textract. Both cost money past a free quota. **ML Kit on-device** is a better fit for MVP testing — no cost, no network latency, no API key setup. Trade-off: slightly less accurate on messy receipts, which is mitigated by the PRD's mandatory review/edit UX.

---

## 3. Data Model (Firestore)

```
groups/{groupId}
  name: String?
  code: String          // 6-char join code
  createdAt: Timestamp
  memberIds: [userId]

groups/{groupId}/members/{userId}
  name: String
  colorHex: String
  joinedAt: Timestamp

groups/{groupId}/entries/{entryId}
  createdAt: Timestamp
  createdBy: userId
  total: Double?
  // receipt image not persisted — OCR runs locally and the image is discarded
  // (Firebase Storage requires the paid Blaze plan to enable)

groups/{groupId}/entries/{entryId}/items/{itemId}
  name: String
  price: Double
  confidence: Float      // from ML Kit — low-confidence items highlighted
  assignedUserIds: [userId]

users/{userId}/itemHistory/{normalizedItemName}
  lastAssignedUserIds: [userId]   // powers "Same as last time?"
```

---

## 4. Milestones

### M0 — Project Setup ✅ (done)
- Gradle + Kotlin + Compose scaffold
- Firebase + ML Kit + CameraX dependencies wired
- Navigation skeleton with stub screens
- `.gitignore` for `google-services.json`, keystores, secrets

**User to do:**
1. Create a Firebase project at console.firebase.google.com (free Spark plan)
2. Add Android app with package `divvvi.app`
3. Download `google-services.json` → drop into `app/`
4. Enable **Anonymous Auth** and **Firestore** in the Firebase console
   (Storage is *not* required — it now needs the paid Blaze plan to enable, and the MVP does not persist receipt images)

### M1 — Group Management (2–3 days)
- Anonymous auth on first launch
- Create group → generate 6-char code + shareable link
- Join by code or `https://divvvi.app/join/{code}` deep link
- Add display name + pick avatar color
- Enforce max 5 members
- Members list screen

### M2 — Receipt Capture + OCR (3–5 days) — highest risk
- CameraX capture with framing guide overlay
- Fallback: gallery upload
- ML Kit `TextRecognition` → raw text blocks
- **Parser:** regex + heuristics to pull `item name | price` pairs and a total
- Store per-item confidence

### M3 — Review Screen (2–3 days) — CRITICAL UX
- Inline-editable list (name + price)
- Add / delete rows
- Low-confidence items: yellow accent + hint text
- Numeric validation on price
- Non-blocking warning when sum(items) ≠ total

### M4 — Item Assignment (2 days)
- Default: every member assigned to every item
- Avatar chip row per item — tap to toggle
- Validation: each item must have ≥1 assignee
- "Same as last time?" suggestion sourced from `itemHistory`

### M5 — Confirm + History (1–2 days)
- Final review screen → confirm button
- Persist entry + items + assignments to Firestore
- History list (date, items, assignments)

### M6 — Polish + real-world test (2–3 days)
- Empty / loading / error states
- OCR failure → manual entry fallback
- Measure scan → confirm time on real receipts with roommates

**Total MVP estimate:** ~3 weeks part-time, solo.

---

## 5. Phase 2 (post-MVP)

- Summary & balance calculation (per-user totals, "who owes whom")
- Payment tracking (payer selection, paid/unpaid)
- Balance dashboard with settlement suggestions (greedy algorithm)

---

## 6. Risks & Mitigations

| Risk | Mitigation |
|---|---|
| OCR accuracy on messy receipts | Strong review/edit UX; confidence highlighting; manual-entry fallback |
| User trust if extraction is wrong | Never auto-submit; always require explicit confirm |
| ML Kit less accurate than Vision API | Acceptable for MVP testing; can swap to Vision API later behind the same interface |
| Deep link domain not owned yet | Use plain `divvvi://` scheme or manual code entry until `divvvi.app` is registered |

---

## 7. Suggested Next Actions

1. **You:** create Firebase project + drop `google-services.json` into `app/`.
2. **You:** continue experience design — focus first on the **Review screen** (highest UX leverage).
3. **Me (next dev session):** implement M1 (group management) end-to-end.
