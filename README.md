# java-qr-attendance

Just a QR code attendance demo app for my sister's representation, but written in java

## How to run

1. Download java 21 from [here](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)
2. Set your ngrok authtoken in `src/main/resources/application.yml` (get one at
   [dashboard.ngrok.com](https://dashboard.ngrok.com)), or disable the tunnel with
   `ngrok.enabled: false`
3. Run with `gradlew bootRun`, or build a .jar with `gradlew build` and run it
   with `java -jar build/libs/<filename>.jar`

Note: the bundled ngrok native library is Windows x86_64 only; on other platforms
run with `ngrok.enabled: false`.

## History

This repository also holds the original Python (Flask + Excel) implementation
that this Java app replaced. Its files were removed in the "Clear and rewrite"
commit, but its full history is preserved — check out any commit before that
one to browse or run it.

## Note

**These code for educational purposes only. It is not secure and not optimized for production use.**

_Good luck, sis!_
