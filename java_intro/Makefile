# Forcer l'utilisation de bash
SHELL := /bin/bash

# Définition des variables
SRC_DIR = src
BIN_DIR = bin
JAVAC = javac
JAVA = java
RM = rm -rf

# Créer le dossier bin si nécessaire
$(BIN_DIR):
	@mkdir -p $(BIN_DIR)

# Trouver tous les fichiers source Java
SRC_FILES = $(shell find $(SRC_DIR) -name "*.java")

# Compiler tous les fichiers en une seule commande pour éviter les erreurs de dépendance
all: $(BIN_DIR)
	@$(JAVAC) -d $(BIN_DIR) -cp $(BIN_DIR) -g $(SRC_FILES)
# Ajout de -Xlint pour gérer les warnings
# @$(JAVAC) -d $(BIN_DIR) -cp $(BIN_DIR) -Xlint -g $(SRC_FILES)

# Compiler un fichier spécifique avec autocomplétion
compile:
	@echo "Sélectionnez un fichier Java à compiler :"
	@select file in $(SRC_FILES); do \
		$(JAVAC) -d $(BIN_DIR) -cp $(BIN_DIR) $$file; \
		break; \
	done

# Exécuter une classe spécifique (exclure demos/ et models/)
run:
	@echo "Sélectionnez une classe à exécuter :"
	@select class in $(shell find $(BIN_DIR) -name "*.class" \
		! -path "$(BIN_DIR)/demos/*" ! -path "$(BIN_DIR)/models/*" \
		| sed 's|$(BIN_DIR)/||' | sed 's|.class||' | sed 's|/|.|g'); do \
		$(JAVA) -cp $(BIN_DIR) $$class; \
		break; \
	done

# Exécuter la démo principale
run-demo:
	@$(JAVA) -cp $(BIN_DIR) demos.DemoLauncher

# Supprimer un fichier .class spécifique avec autocomplétion
clean-file:
	@echo "Sélectionnez un fichier .class à supprimer :"
	@select file in $(shell find $(BIN_DIR) -name "*.class" 2>/dev/null); do \
		$(RM) $$file; \
		break; \
	done

# Nettoyage : supprimer tout le contenu de bin
clean:
	@$(RM) $(BIN_DIR)
