#!/bin/bash

# Verificar que se pasaron argumentos
if [ "$#" -ne 1 ]; then
  echo "Uso: $0 archivo.wat archivo.js"
  exit 1
fi


wat_file=$1





# Ejecutar el archivo JavaScript con Node.js
node "$js_file" < datos.bin

