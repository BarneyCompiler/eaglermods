var PluginAPI = {};
PluginAPI.events = {};
PluginAPI.events.types = [];
PluginAPI.events.listeners = {};
PluginAPI.globals = {};
PluginAPI.version = version;
PluginAPI.addEventListener = function addEventListener(name, callback) {
  if (PluginAPI.events.types.includes(name)) {
    if (!Array.isArray(PluginAPI.events.listeners[name])) {
      PluginAPI.events.listeners[name] = [];
    }
    PluginAPI.events.listeners[name].push(callback);
    console.log("Added new event listener.");
  } else {
    throw new Error("This event does not exist!");
  }
};
PluginAPI.events.newEvent = function newEvent(name) {
  PluginAPI.events.types.push(name);
};
PluginAPI.events.callEvent = function callEvent(name, data) {
  if (
    !PluginAPI.events.types.includes(name) ||
    !Array.isArray(PluginAPI.events.listeners[name])
  ) {
    return;
  }
  PluginAPI.events.listeners[name].forEach((func) => {
    func(data);
  });

  PluginAPI.globals._initUpdate();
};
PluginAPI.updateComponent = function updateComponent(component) {
  if (
    typeof component !== "string" ||
    PluginAPI[component] === null ||
    PluginAPI[component] === undefined
  ) {
    return;
  }
  if (!PluginAPI.globals || !PluginAPI.globals.onGlobalsUpdate) {
    return;
  }
  if (!PluginAPI.globals.toUpdate) {
    PluginAPI.globals.toUpdate = [];
  }
  if (PluginAPI.globals.toUpdate.indexOf(component) === -1) {
    PluginAPI.globals.toUpdate.push(component);
  }
};
PluginAPI.require = function require(component) {
  if (
    typeof component !== "string"
  ) {
    return;
  }
  if (!PluginAPI.globals || !PluginAPI.globals.onRequire) {
    return;
  }
  PluginAPI.globals.onRequire(component);
}
PluginAPI.globals._initUpdate = function _initUpdate() {
  if (!PluginAPI.globals.toUpdate) {
    PluginAPI.globals.toUpdate = [];
  }
  PluginAPI.globals.toUpdate.forEach((id) => {
    PluginAPI.globals.onGlobalsUpdate(id);
  });
  PluginAPI.globals.toUpdate = [];
};
window.PluginAPI = PluginAPI;
