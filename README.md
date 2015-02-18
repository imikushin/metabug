# metabug

Bug: ClojureScript loses metadata about macro-produced defs with names containing generated symbols.
Macro-produced defs named without generated symbols still retain their metadata.

The project in this repo is an illustration of this behaviour.

## Tests

`metabug.test` namespace contains the test cases.
The following command will compile the code to JavaScript and run it with Node.js:

    lein do clean, cljsbuild once


## Setup

You might need to install source map support before running the tests:

    lein npm install


## License

Copyright © 2014 Ivan Mikushin

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
