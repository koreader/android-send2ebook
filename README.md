# send2ebook-android

## Decription
send2ebook lets you send articles found on PC/Android phone to your Ebook reader.

## How it works
Open your faviourite Android web browser, then `Share via`, choose `send2ebook`.
On your Ebook reader download articles (Currently Koreader send2ebook plugin is in progress [#3681](https://github.com/koreader/koreader/pull/3681))

## Technical solution
PC/Android phone -> gets url -> download -> converts to epub -> stores to server(ftp) -> Epub reader downloads (and removes) epub from ftp (currently only Koreader has plugin but feel free to add it to your Ebook reader program;)

## Main library (console UI) :
https://github.com/mwoz123/send2ebook
