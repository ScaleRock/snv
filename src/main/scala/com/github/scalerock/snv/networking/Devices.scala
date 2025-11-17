/*
MIT License

Copyright (c) 2025 ScaleRock

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.github.scalerock.snv.networking


enum Devices {
  case switch_l2      // Basic Layer 2 switch
  case core_switch    // High-performance core switch
  case router         // General-purpose router
  case router_ap      // Router combined with wireless access point
  case windows_server // Windows server
  case linux_server   // Linux server
  case domain_controller // Active Directory domain controller
  case mail_server    // Mail server
  case file_server    // File storage server
  case vpn_server     // VPN server appliance
  case windows_desktop // Windows PC
  case linux_desktop   // Linux PC
  case macos_desktop   // Mac desktop
  case laptop          // Laptop
}

def getIconResourcePath(dev: Devices): String =
  if dev == null then null
  else s"/com/github/scalerock/snv/icons/Devices/${dev.toString}.png"