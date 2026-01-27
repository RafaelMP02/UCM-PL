# C-Horizon Compiler

### English

**C-Horizon** is a programming language designed as a university project for the "Language Processors" course (2023-2024), inspired by **C++**. 

The language supports structured and object-oriented programming with features such as:
* **Nested Scopes**: Support for local variables and symbol table management using `{}`.
* **Advanced Type System**: Includes basic types (`int`, `bool`, `void`) and complex types like `pointers`, `arrays`, `structs`, and `classes`.
* **Functional Types**: Support for functions as first-class citizens, including parameter passing by reference (default) and by value (using the `$` operator).
* **Control Structures**: Implementation of `while`, `for`, `if/elsif/else`, and `switch`.
* **Memory Management**: Support for pointers and dynamic memory allocation via the `new` operator.

This repository contains the full compiler implementation, including:
* **Lexical Analysis**: Token recognition and error detection.
* **Syntax Analysis**: Tree construction and scope recovery.
* **Semantic Analysis**: Binding, type checking, and function overloading.
* **Error Handling**: Detailed reporting of errors including file position (row and column).
* **Code Generation**: Final compilation stage.

📄 The complete language specification is available in the documentation folder.

### Usage

The compiler processes source files (typically `.txt` or custom extensions) and generates executable code.
```bash
# Basic usage: compile a source file
java Main path/to/file.txt
