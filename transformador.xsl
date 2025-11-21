<?xml version="1.0" encoding="UTF-8" ?>
<stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/productos">
        <html lang="es">
            <head>
                <meta charset="utf-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
                      crossorigin="anonymous"/>
                <title>Prueba Trimestral 1 | Jorge Vizuete</title>
            </head>
            <body>
                <div class="container mt-4">
                    <h1>Productos</h1>
                    <div class="col">
                        <xsl:for-each select="productos/producto">
                            <div class="card" style="margin: 10px">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <xsl:value-of select="nombre"/>
                                    </h5>
                                    <p class="card-text">
                                        <ul>
                                            <li>
                                                PRECIO: <xsl:value-of select="precio"/> €
                                            </li>
                                            <li>
                                                CATEGORIA: <xsl:value-of select="categoria"/>
                                            </li>
                                        </ul>
                                    </p>
                                </div>
                            </div>
                        </xsl:for-each>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</stylesheet>