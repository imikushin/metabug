# metabug

Bug: ClojureScript loses metadata about macro-produced defs with names containing generated symbols.
Macro-produced defs named without generated symbols still retain their metadata.

The project in this repo is an illustration of this behaviour.

## Setup

Install npm dependencies (source map support):

    lein npm install

## Run

To see the results, run:

    lein do clean, cljsbuild once


## License

Copyright © 2014 Ivan Mikushin

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
