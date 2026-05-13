# Recipe Hider

A tiny client-side mod for **NeoForge 1.21.1** that declutters JEI by hiding decorative-block spam from Chipped and Rechiseled, while leaving the tools you actually use visible.

## What it does

| Mod | What's hidden in JEI |
| --- | --- |
| **Chipped** | Everything (all decorative blocks *and* the workbenches) |
| **Rechiseled** | Everything except `rechiseled:chisel` |
| **Rechiseled: Create** | Everything |
| **Chisel & Bits** | Nothing — it only adds a couple rows of items, no clutter |

Hidden items are removed from JEI's ingredient panel via `IIngredientManager.removeIngredientsAtRuntime`, so their recipes also become unreachable through the UI. **No recipes are deleted** — the items still exist in-world, you just don't see them in JEI.

## Requirements

- Minecraft **1.21.1**
- NeoForge **21.1.0+**
- JEI **19.x**

## Install

Drop the jar into your `mods/` folder alongside JEI. That's it.

## Notes

- **Client-side only.** Safe to install on the client without changing the server.
- **Rechiseled's chisel still works on Chipped blocks.** The Rechiseled chisel reads chiselable blocks from the block registry, not from JEI, so hiding Chipped from JEI has no effect on what you can chisel.
- **No config file.** The rules are hardcoded — if you want different behavior, edit `JEIHiderPlugin.java` and rebuild.

## License

MIT
