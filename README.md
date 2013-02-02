PvPoints
========
Track yo' player's kills!

**Current supported MC version**: 1.4.*

What is PvPoints?
-----------------
PvPoints allows you to track your player's kills, and view them on a leaderboard.

[Download PvPoints](https://github.com/jackwilsdon/PvPoints/blob/master/jars/PvPoints.jar?raw=true "Latest Release")
-----------------

Configurable Options
--------------------
 - **Chat messages** - Togglable, with parsable variables such as **%PLAYERPOINTS%** and **%PLAYER%**
   - Join messages
   - Leave messages
   - Kill messages
 - **Points taken/given**
   - Add points on kill
   - Subtract points on death
      - Whether or not points should be subtracted on PvP

Commands
--------
 - `/pvpoints` - aliased to `/pv`
   - No parameters - Display your current statistics
   - `reset [username]` - Resets a player's (or your own) statistics
   - `add <username>` - Add a player to PvPlayers (should not be used, as players are auto-added on join)
   - `help [command]` - Shows help for a certain, or all commands.
   - `scores` - Display's the current leaderboards (top 10, sorted by points)
   - **Optional parameters are denoted as `[option]`**

Permissions
-----------
 - `/pvpoints` - no permission required to view statistics
 - `/pvpoints reset` - pvpoints.reset.self
 - `/pvpoints reset username` - pvpoints.reset.other
 - `/pvpoints add username` - pvpoints.add
 - `/pvpoints help [command]` - pvpoints.help
 - `/pvpoints (any invalid option will display help)` - pvpoints.help

Changelog
---------
 - **1.0**
   - Added basic functionality
   - Added PluginMetrics (MCStats.org)
 - **1.1**
   - Fixed config.yml line endings
 - **1.2**
   - Complete re-code from bottom up
   - Add proper commenting to the files
   - Improve features, including commands
 - **1.2.1** (minor update)
   - Added configurable `non-pvp` option, allowing the configuration of whether to subtract a point on a non-pvp death
 - **1.3** (semi-major update)
   - Added leaderboards command, accessible through the use of `/pvpoints scores`
 - **1.3.1** (minor update)
   - Fixed bug with top 10 on leaderboards
   - Added average points graph to MCStats metrics