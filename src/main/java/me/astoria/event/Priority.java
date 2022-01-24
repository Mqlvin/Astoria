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
Description: The enums used by the event system, to indicate the priority of a listening method.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.3
--------------------------------------------------------------------
*/

package me.astoria.event;

public enum Priority {
    LOWEST(4),
    LOW(3),
    NORMAL(2),
    HIGH(1),
    HIGHEST(0);

    private final int level;
    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
