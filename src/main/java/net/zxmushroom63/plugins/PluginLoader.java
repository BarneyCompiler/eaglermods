package net.zxmushroom63.plugins;

import org.teavm.jso.JSBody;

public class PluginLoader {
    public static String[] plugins = {};

    @JSBody(params = { "plugins" }, script = "window.pluginLoader(plugins);")
    public static native void loadPlugins(String[] plugins);

    @JSBody(params = {}, script = "window.pluginLoader = function pluginLoader(pluginsArr) {\r\n" + //
            "  if (!window.eaglerPLoaderMainRun) {\r\n" + //
            "    var searchParams = new URLSearchParams(location.search);\r\n" + //
            "    searchParams.getAll(\"plugin\").forEach((pluginToAdd) => {\r\n" + //
            "      console.log(\r\n" + //
            "        \"EaglerPL: Adding plugin to loadlist from search params: \" + pluginToAdd\r\n" + //
            "      );\r\n" + //
            "      pluginsArr.push(pluginToAdd);\r\n" + //
            "    });\r\n" + //
            "    if (\r\n" + //
            "      !!eaglercraftXOpts &&\r\n" + //
            "      !!eaglercraftXOpts.plugins &&\r\n" + //
            "      Array.isArray(eaglercraftXOpts.plugins)\r\n" + //
            "    ) {\r\n" + //
            "      eaglercraftXOpts.plugins.forEach((pluginToAdd) => {\r\n" + //
            "        console.log(\r\n" + //
            "          \"EaglerPL: Adding plugin to loadlist from eaglercraftXOpts: \" +\r\n" + //
            "            pluginToAdd\r\n" + //
            "        );\r\n" + //
            "        pluginsArr.push(pluginToAdd);\r\n" + //
            "      });\r\n" + //
            "    }\r\n" + //
            "    window.eaglerPLoaderMainRun = true;\r\n" + //
            "  }\r\n" + //
            "  function checkPluginsLoaded(totalLoaded, identifier) {\r\n" + //
            "    console.log(\r\n" + //
            "      \"EaglerPL: Checking if Plugins are finished :: \" +\r\n" + //
            "        totalLoaded +\r\n" + //
            "        \"/\" +\r\n" + //
            "        pluginsArr.length\r\n" + //
            "    );\r\n" + //
            "    if (totalLoaded >= pluginsArr.length) {\r\n" + //
            "      clearInterval(identifier);\r\n" + //
            "      window.pluginGracePeriod = false;\r\n" + //
            "      console.log(\r\n" + //
            "        \"EaglerPL: Checking if Plugins are finished :: All plugins loaded! Grace period off.\"\r\n" + //
            "      );\r\n" + //
            "    }\r\n" + //
            "  }\r\n" + //
            "  function methodB(currentPlugin) {\r\n" + //
            "    try {\r\n" + //
            "      console.log(\"EaglerPL: Loading \" + currentPlugin + \" via method B.\");\r\n" + //
            "      var script = document.createElement(\"script\");\r\n" + //
            "      script.src = currentPlugin;\r\n" + //
            "      script.setAttribute(\"data-plugin\", currentPlugin);\r\n" + //
            "      script.setAttribute(\"data-isplugin\", true);\r\n" + //
            "      script.onerror = () => {\r\n" + //
            "        console.log(\r\n" + //
            "          \"EaglerPL: Failed to load \" + currentPlugin + \" via method B!\"\r\n" + //
            "        );\r\n" + //
            "        script.remove();\r\n" + //
            "        totalLoaded++;\r\n" + //
            "      };\r\n" + //
            "      script.onload = () => {\r\n" + //
            "        console.log(\r\n" + //
            "          \"EaglerPL: Successfully loaded \" + currentPlugin + \" via method B.\"\r\n" + //
            "        );\r\n" + //
            "        totalLoaded++;\r\n" + //
            "      };\r\n" + //
            "      document.body.appendChild(script);\r\n" + //
            "    } catch (error) {\r\n" + //
            "      console.log(\r\n" + //
            "        \"EaglerPL: Oh no! The plugin \" + currentPlugin + \" failed to load!\"\r\n" + //
            "      );\r\n" + //
            "      totalLoaded++;\r\n" + //
            "    }\r\n" + //
            "  }\r\n" + //
            "  window.pluginGracePeriod = true;\r\n" + //
            "  var totalLoaded = 0;\r\n" + //
            "  var loaderCheckInterval = null;\r\n" + //
            "  pluginsArr.forEach((c) => {\r\n" + //
            "    let currentPlugin = c;\r\n" + //
            "    console.log(\"EaglerPL: Starting \" + currentPlugin);\r\n" + //
            "    try {\r\n" + //
            "      var req = new XMLHttpRequest();\r\n" + //
            "      req.open(\"GET\", currentPlugin);\r\n" + //
            "      req.onload = function xhrLoadHandler() {\r\n" + //
            "        console.log(\"EaglerPL: Loading \" + currentPlugin + \" via method A.\");\r\n" + //
            "        var script = document.createElement(\"script\");\r\n" + //
            "        try {\r\n" + //
            "          script.src = \"data:text/javascript;base64,\" + btoa(req.responseText);\r\n" + //
            "        } catch (error) {\r\n" + //
            "          methodB(currentPlugin);\r\n" + //
            "          return;\r\n" + //
            "        }\r\n" + //
            "        script.setAttribute(\"data-plugin\", currentPlugin);\r\n" + //
            "        script.setAttribute(\"data-isplugin\", true);\r\n" + //
            "        script.onerror = () => {\r\n" + //
            "          console.log(\r\n" + //
            "            \"EaglerPL: Failed to load \" + currentPlugin + \" via method A!\"\r\n" + //
            "          );\r\n" + //
            "          script.remove();\r\n" + //
            "          totalLoaded++;\r\n" + //
            "        };\r\n" + //
            "        script.onload = () => {\r\n" + //
            "          console.log(\r\n" + //
            "            \"EaglerPL: Successfully loaded \" + currentPlugin + \" via method A.\"\r\n" + //
            "          );\r\n" + //
            "          totalLoaded++;\r\n" + //
            "        };\r\n" + //
            "        document.body.appendChild(script);\r\n" + //
            "      };\r\n" + //
            "      req.onerror = function xhrErrorHandler() {\r\n" + //
            "        methodB(currentPlugin);\r\n" + //
            "      };\r\n" + //
            "      req.send();\r\n" + //
            "    } catch (error) {\r\n" + //
            "      methodB(currentPlugin);\r\n" + //
            "    }\r\n" + //
            "  });\r\n" + //
            "  loaderCheckInterval = setInterval(() => {\r\n" + //
            "    checkPluginsLoaded(totalLoaded, loaderCheckInterval);\r\n" + //
            "  }, 500);\r\n" + //
            "  console.log(\r\n" + //
            "    \"EaglerPL: Starting to load \" + pluginsArr.length + \" plugins...\"\r\n" + //
            "  );\r\n" + //
            "};\r\n" + //
            "")
    public static native void loadLoader();

    @JSBody(params = { "plugins" }, script = "localStorage.setItem('pl::plugins', JSON.stringify(plugins))")
    private static native void savePlugins(String[] plugins);

    @JSBody(params = {}, script = "try { return JSON.parse(localStorage.getItem('pl::plugins')||'[]') } catch(err) {return []}")
    private static native String[] retrievePlugins();

    public static void savePluginsToLocalStorage() {
        savePlugins(plugins);
    };

    public static void loadPluginsFromLocalStorage() {
        plugins = retrievePlugins();
    };
}