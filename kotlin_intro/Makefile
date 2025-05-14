# Définition des variables
SRC_DIR = src
BIN_DIR = bin
KOTLINC = kotlinc
KOTLIN = kotlin
RM = rm -rf

# Créer le dossier bin si nécessaire
$(BIN_DIR):
	@mkdir -p $(BIN_DIR)

# Trouver tous les fichiers source Kotlin
SRC_FILES = $(shell find $(SRC_DIR) -name "*.kt")

# Compiler tous les fichiers .kt
all: $(BIN_DIR)
	@$(KOTLINC) $(SRC_FILES) -d $(BIN_DIR)

# Exécuter une classe contenant un main() (choix interactif)
run:
	@echo "Sélectionnez une classe à exécuter :"
	@select class in $(shell find $(BIN_DIR) -name "*Kt.class" \
		| sed 's|$(BIN_DIR)/||' | sed 's|.class||' | sed 's|/|.|g'); do \
		$(KOTLIN) -cp $(BIN_DIR) $$class; \
		break; \
	done

# Nettoyer un seul fichier .class
clean-file:
	@echo "Sélectionnez un fichier .class à supprimer :"
	@select file in $(shell find $(BIN_DIR) -name "*.class" 2>/dev/null); do \
		$(RM) $$file; \
		break; \
	done

# Nettoyer complètement
clean:
	@$(RM) $(BIN_DIR)
