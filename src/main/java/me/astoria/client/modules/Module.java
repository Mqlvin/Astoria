package me.astoria.client.modules;

public abstract class Module {
    private String name; // Name of module.
    private String description; // Brief overview of module.
    private String version; // Development version of module.
    private Category category; // Type of module.
    private String originalAuthor; // Original author/idea creator of module.
    private String authorContact; // A link, for example discord tag, profile page or email to contact.
    private String moduleDeveloper; // Developer of the module.
    private String developerContact; // A link, for example discord tag, profile page or email to contact.
    private String saveKey; // The key to point to the save location.
    private Boolean enabled; // Whether the module is enabled or not.

    public Module(String name, String description, String version, Category category, String originalAuthor, String authorContact, String moduleDeveloper, String developerContact, String saveKey, Boolean enabled) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.category = category;
        this.originalAuthor = originalAuthor;
        this.authorContact = authorContact;
        this.moduleDeveloper = moduleDeveloper;
        this.developerContact = developerContact;
        this.saveKey = saveKey;
        this.enabled = enabled;
    }

    public abstract void enable();
    public abstract void disable();

    public void toggle() {
        if(enabled) {
            disable();
        } else {
            enable();
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public Category getCategory() {
        return category;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public String getAuthorContact() {
        return authorContact;
    }

    public String getModuleDeveloper() {
        return moduleDeveloper;
    }

    public String getDeveloperContact() {
        return developerContact;
    }

    public String getSaveKey() {
        return saveKey;
    }

    public void setEnabled(Boolean isEnabled) {
        enabled = isEnabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
