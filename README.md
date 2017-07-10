Hello fellow engineer, this is my implementation of Flickr viewer
Let me tell you about this small application:

Libraries
It's written on Kotlin, it's 2017 and to write new project on Java is not cool. Everything else (retrofit2 / picasso / okHttp3) which is pretty much the standard nowadays. Also I've added paper (https://github.com/pilgr/Paper) as a replacement of shared preferences (I tend to avoid them since API is ugly and there are some issues in case of multi process app)

Architecture:
I've decided to keep it as simple as possible the only «innovation» is binders - handy subclass from ViewHolder and providers which contains all the loading logic. I absolutely insist on separation loading from adapters but have doubts about my implementation -- given more time I would choose more flexible option. Also I've faced problem of keeping aspect ratio of the images on the page and after reading stack overflow decided to hack GridLayoutManager, it's ugly but works, for more advanced project I would implement my own GridLayoutManager (Other options seems to be quite heavy since they add additional complexity to view hierarchy)

Testing:
Only manual. Given more time I would implement UI testing with different test providers.
