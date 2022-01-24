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
Called: When a key is pressed.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.3
--------------------------------------------------------------------
*/

package me.astoria.event.impl.client;

import me.astoria.event.Event;

public class KeyPressEvent extends Event {
    private final Boolean isRepeatEvent;
    private final Integer keyCode;

    public KeyPressEvent(Integer keyCode, Boolean isRepeatEvent) {
        this.isRepeatEvent = isRepeatEvent;
        this.keyCode = keyCode;
    }

    public Boolean isRepeatEvent() {
        return this.isRepeatEvent;
    }

    public Integer getKeyCode() {
        return keyCode;
    }
}
