# WarpIcons
WarpIcons is a simple bukkit plugin to provide warps in a UI fashion, with different categories of warps to choose from. The plugin uses a 1.13+ feature called Custom Model Data to determine what a warp should look like.

Supported version: 1.13+

## Commands
`/warp` - Displays default warp category (If enabled).
`/warp {Category Name}` - Displays a specific warp category
`/warp reload` - Reloads the warp categories

## UI Layouts
### Gappy:
![Gappy Layout](https://i.imgur.com/UqN0h0C.png "Gappy Layout")
### Default:
![Default Layout](https://i.imgur.com/eXkHtB7.png "Default Layout")

## Yaml Layout
In order to create a Warp Section, you need to create one maunally under `Warp Categories` folder in the plugin folder.

To create one, create a YAML file by naming the file with the category name ending in `.yml`. **Make sure that you contain no spaces within the filename.**

This is what the YAML file should look like (Feel free to copy and paste it in):
```yaml
Title: "Title" # The title is the text that is displayed on the top left of the UI.
Icon Material: "INK_SAC" # What the icon material is binded to. Make sure to use 
Layout: {DEFAULT/GAPPY}
Required Permission: "" # Self explanatory. Leaving it blank will require no permission to open the category.

Warps:
  Warp Name:
    Display Name: "&bA Warp"
    CustomModelData: 1 # Displays the icon look with the custom model data.
    Location:
      ==: org.bukkit.Location
      world: world_name
      x: 0.0
      y: 0.0
      z: 0.0
      pitch: 0.0
      yaw: 0.0
  Warp Name2:
    Display Name: "&bA Warp"
    CustomModelData: 2 # Displays the icon look with the custom model data.
    Location:
      ==: org.bukkit.Location
      world: world_name
      x: 0.0
      y: 0.0
      z: 0.0
      pitch: 0.0
      yaw: 0.0
```
be sure to run the reload command or restart the server to have the new changes come to effect.
