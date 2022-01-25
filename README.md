## **Astoria PVP Client**
Astoria is a Minecraft PVP client, dedicated around ease of use, performance and customization. The whole client will also be open-source.
###### *Note: Name is subject to change. I really hate it.*

------------

## License
GNU GENERAL PUBLIC LICENSE - Version 3, 29 June 2007

    Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/
    Everyone is permitted to copy and distribute verbatim copies
    of this license document, but changing it is not allowed.

    The GNU General Public License is a free, copyleft license for
    software and other kinds of works.

------------

## Contribution Guidelines
###### *Note: As of right now, contributions aren't being taken. This client is open-source however, and I do have plans for contribution later.*

### **All classes must contain a license, description, date modified and author.**

    /*
    --------------------------------------------------------------------
    Copyright (C) 2021-2022 by Mqlvin | Contact: %%license_contact%%
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    --------------------------------------------------------------------
    Description: The object used to store listening methods in the event bus.
    Authors: [Mqlvin/melvinkelvin#6328]
    Modified: b0.3
    --------------------------------------------------------------------
    */

You do not need to change anything in the license, however you will need to change the class information.
- Description - Include a brief overview of the class, explaining roughly what it does. If you are editing a class, you may also add significant changes into the class description.
- Authors - After contribution, you may add yourself to the author list. It follows a strict format of <name/contact>. Contact can be either a Discord tag or an email address. The author list is formatted as an array, which can be seen in the example below.
- Modified - After contribution, you may also edit the modified tag to the client version. Whether you made a new class or edited an existing one, change modified to the **current client version**.

Below is an example of what to do if you are editing a class:

    --------------------------------------------------------------------
    Description: The object used to store listening methods in the event bus. Also holds priority.
    Authors: [Mqlvin/melvinkelvin#6328, newPerson/newPerson#0000]
    Modified: b0.4
    --------------------------------------------------------------------
### **Malicious Code**  

Attempting to incorporate malicious code is against contribution guidelines. If you attempt this, I will accept no further pull requests from you. I reserve the right to deem any code malicious by my own guidelines.  
Malicious code includes, but is not limited to things such as:

- Minecraft cheats
- Ransomware
- Token stealers / Key loggers
- Cryptocurrency miners
- Adware
- Spyware

### **Module Development**
Do not add modules to the client directly, unless you feel they are core mods which Minecraft needs. If you developed a custom module, please upload it to the marketplace instead.

------------

## Current Modules
|  Name | Category  | Description  |
| ------------ | ------------ | ------------ |
| WindowedFullscreen  |  Utility | Allows the user to use multiple monitors.  |
| Fullbright  |  Render | Allows the user to see in dark places.  |
| ...  | ...  |  ... |

###### *Note: This list contains only default modules. No externally developed modules should be added.*

------------

## Current Events
| Event Name  | Description  | Parameters  |
| ------------ | ------------ | ------------ |
| **Client**  |   |   |
| ClientInitialisedEvent  | Called once the Astoria initialisation has completed.  | N/A  |
| ChatReceivedEvent  | Called on the client receiving a chat message.  | IChatComponent message  |
| PlayerChatEvent  | Called on a player sending a chat message.  | String message  |
| **Input**  |   |   |
| LeftClickEvent  | Called when a left click is executed in-game.  | N/A  |
| RightClickEvent  | Called when a right click is executed in-game.  | N/A  |
| KeyPressEvent  | Called when a key is pressed.  | Integer keyCode, Boolean isRepeatEvent  |
| KeyReleaseEvent  | Called when a key is released.  | Integer keyCode, Boolean isRepeatEvent  |
| **Utility/Misc**  |   |   |
| ToggleFullscreenEvent  | Called when the player toggles fullscreen.  | Boolean isNowFullscreen |

###### *Note: I may make a documentation page as this list may become quite long.*

------------

## Development Status
- [ ] Base Modules
- [x] Module System
- [ ] Module Addon Loader
- [ ] API Wrapper
- [x] Event System
- [ ] Notification System
- [ ] GUI Framework
- [ ] Main GUI
- [ ] Cosmetic System

###### *Note: Tick means at a usable standard, or fully developed. Some items from the list may have been started but are not currently in a usable state.*

------------

## SDK Overview
An SDK will be made to implement your own modules. There is a possibility of a visual code editor, so all players will be able to create their own modifications with little development experience.

------------

## Astoria Projects
###### *Note: I will make different repositories for these projects, with more in-depth information.*
- [ ] Starter Development Kit for modules.
- [ ] Launcher for Astoria Client.
- [ ] API plugin for servers.
