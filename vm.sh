#!/bin/bash

## Instalar o VM
cd compilador/vm-1.7/
chmod +x configure
sudo apt-get install ocaml-native-compilers
sudo apt-get install gobject-introspection
sudo apt-get install -y liblablgtk2-ocaml-dev 
./configure
sudo make

