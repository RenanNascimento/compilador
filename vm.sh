#!/bin/bash

## Instalar o VM
cd vm-1.7/
chmod +x configure
sudo apt-get install -y gobject-introspection
sudo apt-get install -y liblablgtk2-ocaml-dev 
./configure
sudo make

