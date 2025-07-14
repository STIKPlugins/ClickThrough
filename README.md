# ClickThrough – Sign & ItemFrame Click-Through Plugin

**ClickThrough** lets players click through signs and item frames (just like the ClickThrough mod), with sneak-bypass and hot-reload support via `/clickthrough reload`.

**Join our** [**Discord**](https://discord.gg/YGzA4UxzFB) — discover other cool plugins, suggest new features, or get help with configurations.

### Features ✨

- **Sign Click-Through**  
  Allows clicks to pass through signs.

- **ItemFrame Click-Through**  
  Let clicks go through item frames.

- **Sneak Bypass**  
  Hold shift to interact normally with signs and item frames.

- **Hot Reload**  
  Reload the plugin config on-the-fly with `/clickthrough reload`.

### Main config (`config.yml`) ⚙️

<details>
  <summary><strong>config.yml</strong></summary>

  ```yaml
# Join our discord - https://discord.gg/YGzA4UxzFB you can find other cool plugins there.
# Permissions
# "clickThrough.reload" - permission required to use "/clickthrough reload" command

sign:
  doClickThrough: true
  # if player is sneaking, do not click through
  # recommended true, otherwise player can't change sign text
  ignoreWhenPlayerSneaking: true

itemFrame:
  doClickThrough: true
  # if player is sneaking, do not click through
  # recommended true, otherwise player can't change item in ItemFrame
  ignoreWhenPlayerSneaking: true
  # if true, click through only if itemFrame has item
  requiredItemInFrame: true

# supports MiniMessage
noPermissionToReload: "<red>✘ <white>You don't have permission to reload Config!"
configReloaded: "<green>✔ <white>Config reloaded!"
````

</details>

### Permissions 🔐

| Permission Node       | Description                        |
| --------------------- | ---------------------------------- |
| `clickThrough.reload` | Allows reloading the plugin config |

### Commands 📟

| Command                | Description                     |
| ---------------------- | ------------------------------- |
| `/clickthrough reload` | Reload the plugin configuration |
