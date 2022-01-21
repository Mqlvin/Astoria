## **Astoria PVP Client**
Astoria is a Minecraft PVP client, dedicated around ease of use, performance and customization. The whole client will also be open-source.
###### *Note: Name is subject to change.*

### License
...

### Current Modules
|  Name | Category  | Description  |
| ------------ | ------------ | ------------ |
| WindowedFullscreen  |  Utility | Allows the user to use multiple monitors.  |
| ...  | ...  |  ... |

### Current Events
| Event Name  | Description  | Parameters  |
| ------------ | ------------ | ------------ |
| **Client**  |   |   |
| ClientInitialisedEvent  | Called once the Astoria initialisation has completed.  | N/A  |
| **Input**  |   |   |
| LeftClickEvent  | Called when a left click is executed in-game.  | N/A  |
| RightClickEvent  | Called when a right click is executed in-game.  | N/A  |
| KeyPressEvent  | Called when a key is pressed.  | Integer keyCode, Boolean isRepeatEvent  |
| KeyReleaseEvent  | Called when a key is released.  | Integer keyCode, Boolean isRepeatEvent  |
| **Utility/Misc**  |   |   |
| ToggleFullscreenEvent  | Called when the player toggles fullscreen.  | Boolean isNowFullscreen |


### Development Status
- [ ] Base Modules
- [ ] Backend Module System
- [ ] API Wrapper
- [x] Event System
- [ ] Notification System
- [ ] GUI Framework
- [ ] Main GUI
- [ ] Cosmetic System

### SDK Overview
An SDK will be made to implement your own modules. There is a possibility of a visual code editor, so all players will be able to create their own modifications with little development experience.

### Astoria Projects
###### *Note: I will make different repositories for these projects, with more in-depth information.*
- [ ] Starter Development Kit for modules.
- [ ] Launcher for Astoria Client.
- [ ] API plugin for servers.
