<html lang="en">
<head>
    <link rel="stylesheet" href="../stylesheet.css">
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<table>
    <span id="load_date">${localDateTime}</span>
    <#if time_restriction??>
    <div><span id="time_restriction">The code will be available for <u>${time_restriction}</u> seconds</span></div>
    </#if>
    <#if views_restriction??>
    <div><span id="views_restriction"><u>${views_restriction}</u> more views allowed</span></div>
    </#if>
    <pre id="code_snippet"><code>${code}</code></pre>
</table>
</body>
</html>