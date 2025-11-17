{ pkgs ? import <nixpkgs> { } }:

let
  libs = [
    pkgs.glib
    pkgs.xorg.libX11
    pkgs.gtk3
    pkgs.cairo
    pkgs.pango
    pkgs.gdk-pixbuf
    pkgs.xorg.libXtst
    pkgs.xorg.libXxf86vm
    pkgs.libGL
  ];
in pkgs.mkShell {
  buildInputs = [
    pkgs.jdk
    pkgs.scala
    pkgs.sbt
  ] ++ libs;
  shellHook = ''
    export LD_LIBRARY_PATH=${pkgs.lib.makeLibraryPath libs}
  '';
}
