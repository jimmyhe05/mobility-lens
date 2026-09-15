# Mobility Lens

A single-screen Android app built with Kotlin and Jetpack Compose that walks through six dimensions that separate mobile app design from desktop app design.

## Project Report

Screenshots and the full generative-AI disclosure are included in the accompanying PDF report (`Mobility Lens Project Report.pdf`). The report also covers the emulator/API level used, SDK configuration, and the rotation observation.

## Screenshots

The same screenshots included in the report are also available in the `/screenshots` directory of this repository:

- App running on the first dimension
- A different dimension after user interaction
- Validation response to blank input
- Successful response to valid input

## Generative-AI Disclosure

**Tool used:** Claude Sonnet 5 chat

**Prompts:** I asked how to structure the app, why the screen wasn't scrollable when rotated sideways, how Button's onClick logic works, and for typography suggestions for different sections of text. I also asked it to fix grammar and sentence structure in my strings.xml text.

**Suggestions produced:** It explained landscape mode was cutting off content because the Column had no scroll modifier and suggested adding `.verticalScroll(rememberScrollState())`, and suggested distinct text styles in `Type.kt` so each text section looked visually different.

**What I accepted, changed, or rejected:** I accepted scroll fix and the typography suggestions but picked the specific font weights/sizes myself. Paraphrased strings.xml text but reworded some lines afterward.

**How I verified the code:** I ran the app in the emulator after each change, tested all six dimensions, the text input, the blank-input warning, a valid Check response, and rotated the emulator to confirm the scroll.

This same disclosure is included in full in the project report PDF.
