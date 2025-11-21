# **SNV – Simple Network Viue**

A lightweight network visualization tool built with Scala 3 and JavaFX.

---

## ⚡ Quick Run (Not Recommended)

This mode uses the JavaFX runtime from SBT,
but consumes **~10× more RAM**, so it's only for development.

```bash
sbt clean run
```

---

## 🚀 Recommended Run (Manual JavaFX Installation Required)

### 1. Build the assembly JAR:

```bash
sbt clean assembly
```

### 2. Run the application:

```bash
java -jar target/scala-3.3.5/SimpleNetworkViue-assembly-0.1.0.jar \
  --module-path=/path/to/javafx-sdk-21.0.9/lib \
  --add-modules=javafx.controls,javafx.fxml
```

---

## 🧊 Nix / NixOS Support

A `shell.nix` file is included to simplify JavaFX setup for Nix and NixOS users.

---

## 📄 License

This project is licensed under the **MIT License**.
See the [`LICENSE`](./LICENSE) file for details.

