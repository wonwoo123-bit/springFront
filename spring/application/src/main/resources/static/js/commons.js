function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
    var winleft = (screen.width - WinWidth) / 2;
    var wintop = (screen.height - WinHeight) / 2;
    var win = window.open(
      UrlStr,
      WinTitle,
      "scrollbars=yes,width=" + WinWidth +
      ",height=" + WinHeight +
      ",top=" + wintop +
      ",left=" + winleft +
      ",resizable=yes,status=yes");
    win.focus();
  }