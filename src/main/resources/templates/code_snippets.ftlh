<html lang="en">
<head>
    <link rel="stylesheet" href="../stylesheet.css">
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<table>
    <#list snippets as snippet>
    <span id="load_date">${snippet.localDateTime}</span>
    <pre id="code_snippet"><code>${snippet.code}</code></pre>
    </#list>
</table>
</body>
</html>