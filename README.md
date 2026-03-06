# C-Horizon

## English

### Project Name
C-Horizon (Custom Language Compiler to WebAssembly)

### Description
C-Horizon is a university compiler project implemented in Java. It parses a custom programming language and performs semantic analysis before generating WebAssembly Text (`.wat`) output. The project includes a small Node.js runtime script for executing compiled WebAssembly modules. you can find all the detailed explanation in the document "Final report.pdf".

### Key Features
- Lexical analysis with JFlex and syntax analysis/AST construction with Java CUP.
- Semantic analysis stages: binding (name resolution) and static type checking.
- Support for core language constructs: functions, `main`, structs/classes, arrays, pointers, conditionals, loops, and function calls.
- Code generation to WebAssembly Text (`.wat`) in the `generator/` folder.

### Tech Stack
- Java (JDK 8+ recommended)
- JFlex (`lib/jflex.jar`)
- Java CUP (`lib/cup.jar`)
- WebAssembly Text (`.wat`) / WebAssembly (`.wasm`)
- Node.js (optional runtime execution)

### Quick Start
```bash
# 1) Run from the parent directory (important because output path is ./Compilador/generator)
cd /path/to/UCM-PL

# 2) Compile Java sources
javac -cp "Compilador/lib/cup.jar:Compilador/src" -d Compilador/bin $(find Compilador/src -name "*.java")

# 3) Compile a test program (generates .wat)
java -cp "Compilador/bin:Compilador/lib/cup.jar" constructorast.Main Compilador/tests/input20.txt

# Output example:
# Compilador/generator/input20.wat
```

Optional (`.wat` -> `.wasm`, requires `wat2wasm`):
```bash
wat2wasm Compilador/generator/input20.wat -o Compilador/generator/input20.wasm
```

## Español

### Project Name
C-Horizon (Compilador de lenguaje personalizado a WebAssembly)

### Description
C-Horizon es un proyecto universitario de compiladores implementado en Java. Analiza un lenguaje de programación propio y realiza análisis semántico antes de generar código WebAssembly Text (`.wat`). El proyecto también incluye un script de ejecución en Node.js para correr módulos WebAssembly. Puedes encontrar toda la explicación detallada en el documento "Documento.pdf".

### Key Features
- Análisis léxico con JFlex y análisis sintáctico/construcción de AST con Java CUP.
- Etapas de análisis semántico: vinculación (resolución de nombres) y comprobación estática de tipos.
- Soporte de construcciones del lenguaje: funciones, `main`, structs/clases, arrays, punteros, condicionales, bucles y llamadas a función.
- Generación de código en WebAssembly Text (`.wat`) dentro de la carpeta `generator/`.

### Tech Stack
- Java (recomendado JDK 8+)
- JFlex (`lib/jflex.jar`)
- Java CUP (`lib/cup.jar`)
- WebAssembly Text (`.wat`) / WebAssembly (`.wasm`)
- Node.js (ejecución opcional)

### Quick Start
```bash
# 1) Ejecuta desde el directorio padre (importante porque la salida se escribe en ./Compilador/generator)
cd /ruta/a/UCM-PL

# 2) Compila las fuentes Java
javac -cp "Compilador/lib/cup.jar:Compilador/src" -d Compilador/bin $(find Compilador/src -name "*.java")

# 3) Compila un programa de prueba (genera .wat)
java -cp "Compilador/bin:Compilador/lib/cup.jar" constructorast.Main Compilador/tests/input20.txt

# Ejemplo de salida:
# Compilador/generator/input20.wat
```

Opcional (`.wat` -> `.wasm`, requiere `wat2wasm`):
```bash
wat2wasm Compilador/generator/input20.wat -o Compilador/generator/input20.wasm
```
