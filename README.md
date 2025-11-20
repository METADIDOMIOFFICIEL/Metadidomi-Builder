# 🚀Multi-Builder premium

**Builder nouvelle génération** avec chiffrement de ressources, protection bytecode, obfuscation intelligente et prise en charge Python. 
**Windows maintenant, macOS et Linux en développement.**

---
 ![](/docs/icon.ico) 
---
### 💝 Soutenir le projet

Si ce builder vous a aidé, merci de soutenir le développement :

[![Sponsor Lygos](https://img.shields.io/badge/Sponsor-Lygos-blue?style=for-the-badge)](https://pay.lygosapp.com/$etsmeta)
![Version](https://img.shields.io/badge/version-1.4.201125-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Stars](https://img.shields.io/github/stars/METADIDOMIOFFICIEL/Metadidomi-Builder?style=social)
![Issues](https://img.shields.io/github/issues/[METADIDOMIOFFICIEL/Metadidomi-BUILDER])

---
# �🚀 Multi-Builder Premium

**Builder nouvelle génération** avec chiffrement de ressources, protection bytecode, obfuscation intelligente et prise en charge Python. 
**Windows maintenant, macOS et Linux en développement.**

---

## 📑 Table des Matières

1. **[À Propos](#-à-propos)** - Présentation générale
2. **[Installation](#-installation)** - Mise en place
3. **[Afficher les Commandes Disponibles](#afficher-toutes-les-commandes-disponibles)** - Liste interactive des commandes
4. **[Démarrage Rapide Electron](#-démarrage-rapide---applications-electron)** - Premiers pas Electron
5. **[Modes de Construction](#-modes-de-construction)** - Options de build Electron
6. **[Protection du Code](#-système-de-protection-avancé)** - Sécurité (Electron et Python)
7. **[Packaging Python](#-packaging-dapplications-python)** - Applications Python
8. **[Comparaison vs electron-builder](#-comparaison-avec-electron-builder)** - Différences et choix
9. **[Roadmap](#-roadmap-vision-multi-plateforme)** - Futures versions
10. **[Support](#-support)** - Aide et contact

---

## 💝 À Propos

Constructeur **professionnel** pour applications Electron et Python exigeant :
- ✅ **Sécurité maximale** - Chiffrement AES-256, bytecode, obfuscation
- ✅ **Builds reproductibles** - 100% déterministe et offline
- ✅ **Customisation totale** - Contrôle complet du processus
- ✅ **Zéro dépendances externes** - Tous les outils embarqués

### ⭐ Soutenir le Projet

Si ce builder vous a aidé, merci de soutenir le développement :

Votre soutien permet de :
- ✅ Développer de nouvelles fonctionnalités
- ✅ Supporter macOS et Linux
- ✅ Améliorer la documentation
- ✅ Corriger les bugs rapidement

---

## 🚀 Installation

### Installation via npm (Recommandée)

**Pour le développement :** Seuls `electron@^39.1.1` et Python 3.11+ sont requis.
[![](https://img.shields.io/badge/info-Commandes%20disponibles-blue)]

### Afficher toutes les commandes disponibles

> **Important :** Avant d'utiliser ces commandes, ajoutez les scripts suivants dans la section `scripts` de votre `package.json` :
>
> ```json
> "help": "node node_modules/metadidomi-builder/build_tools/commands-help.js",
> "help:electron": "node node_modules/metadidomi-builder/build_tools/commands-help.js --type=electron",
> "help:python": "node node_modules/metadidomi-builder/build_tools/commands-help.js --type=python",
> "help:all": "node node_modules/metadidomi-builder/build_tools/commands-help.js --all"
> ```

Pour voir toutes les commandes adaptées à votre projet (Electron ou Python), utilisez la commande d'aide interactive :

```powershell
npm run help           # Affiche toutes les commandes disponibles
npm run help:electron # Affiche uniquement les commandes Electron
npm run help:python   # Affiche uniquement les commandes Python
npm run help:all      # Affiche tout (mode universel)
```

**Exemple d'affichage** :
```
> npm run help

─────────────────────────────
 METADIDOMI BUILDER - COMMANDES DISPONIBLES
─────────────────────────────

1. BUILD
  Crée un installateur Windows NSIS professionnel
  Commande: npm run build
  Alternatives: npx metadidomi-builder
  Sortie: ./dist/MonApp-Setup-1.0.0.exe

2. BUILD:PYTHON
  Build d'application Python en mode console
  Commande: npm run build:python
  Alternatives: npx metadidomi-builder-python
  Sortie: ./dist/MonApp-Setup-1.0.0.exe

...etc
```

La commande détecte automatiquement le type de projet et affiche les commandes adaptées, avec explications et exemples.

Pour plus d'exemples, consultez la section "Exemples Pratiques" plus bas.

#### Option 1 : Installation dans votre projet

```powershell
# Installez le builder comme dépendance de développement
npm install --save-dev metadidomi-builder

# Ajoutez des scripts dans votre package.json
# "scripts": {
#   "build": "metadidomi-builder",
#   "build:lite": "set LITE_BUILD=true && metadidomi-builder",
#   "build:portable": "set CREATE_PORTABLE_EXE=true && metadidomi-builder",
#   "build:python": "node node_modules/metadidomi-builder/build_tools_py/builder.py",
#   "build:python:gui": "node node_modules/metadidomi-builder/build_tools_py/builder.py --gui"
# }

# Puis lancez avec :
npm run build                    # Build Electron standard
# ou
npm run build:lite              # Build Electron optimisé
npm run build:portable          # Exécutable portable
npm run build:python            # Build Python (console)
npm run build:python:gui        # Build Python (GUI)
```

#### Option 2 : Utilisation directe avec npx

```powershell
# Lancez directement sans installation
npx metadidomi-builder
```

#### Option 3 : Installation Globale

```powershell
# Installez globalement
npm install -g metadidomi-builder

# Lancez depuis n'importe où
metadidomi-builder
```

### Installation Manuelle (Avancé)

Si vous préférez installer manuellement :

1. **Clonez le repository**
```powershell
git clone https://github.com/METADIDOMIOFFICIEL/Metadidomi-Builder.git
cd metadidomi-builder
```

2. **Installez les dépendances**
```powershell
npm install
```

3. **Téléchargez les modules vendor** (si absent)
   - Téléchargez `vendor.zip` depuis :
     https://github.com/METADIDOMIOFFICIEL/Metadidomi-Builder/releases/download/1.3.171125/vendor.zip
   - Extrayez dans `build_tools/vendor/`

4. **Lancez le builder**
```powershell
# Depuis le dossier d'installation
node build_tools/builder.js

# Ou depuis votre app (en spécifiant le chemin)
node "C:\chemin-vers\metadidomi-builder\build_tools\builder.js"
```

### Modules Embarqués - 100% Offline

Toutes les dépendances essentielles sont incluses dans `node_modules/metadidomi-builder/build_tools/vendor/` (installation npm) ou `build_tools/vendor/` (installation manuelle) :

```
build_tools/vendor/ (ou node_modules/metadidomi-builder/build_tools/vendor/)
  ├── asar/                    # Packaging et archivage
  ├── bytenode/                # Compilation JavaScript → bytecode V8
  ├── electron-asar/           # ASAR officiel Electron
  ├── electron-packager/       # Empaquetage Electron
  ├── javascript-obfuscator/   # Obfuscation de code JS
  ├── minimist/                # Parsing d'arguments CLI
  ├── rcedit/                  # Édition des ressources Windows
  ├── sharp/                   # Traitement d'images
  ├── tmp/                     # Gestion des fichiers temporaires
  ├── 7zip-bin/                # Compression 7-Zip
  ├── nsis/                    # Créateur d'installateurs NSIS
  ├── upx/                     # Compression d'exécutables
  └── signtool/                # Signature de code Windows (optionnel)
```

**Avantages :**
- ✅ Reproductibilité totale garantie
- ✅ Indépendance réseau complète
- ✅ Pas de dépendances système externes
- ✅ Builds déterministes

### 🔐 Signature de Code (Optionnel)

Le builder intègre un **système automatique de signature** avec deux modes :

#### Mode 1 : Auto-signé (Défaut - Développement)
- ✅ Généré automatiquement lors du premier build
- 📁 Stocké dans `node_modules/metadidomi-builder/build_tools/certs/cert-[hash].pfx` (npm) ou `build_tools/certs/cert-[hash].pfx` (manuel)
- 🔑 Mot de passe dans `cert-[hash].key`
- ⚡ Aucune configuration requise

#### Mode 2 : Certificat Personnalisé (Production)

```powershell
# Option 1 : Fichier dans le dossier par défaut
# Npm: Placer dans node_modules/metadidomi-builder/build_tools/certs/signing.pfx
# Manuel: Placer dans build_tools/certs/signing.pfx

# Option 2 : Variables d'environnement
$env:PFX_PATH="chemin/vers/certificat.pfx"
$env:PFX_PASS="mot-de-passe-certificat"

# Lancer le build avec npm
npm run build

# Ou avec npx
npx metadidomi-builder
```

**Priorité de signature :**
1. Module personnalisé `build_tools/signing.js` (si présent)
2. `signtool.exe` local dans `build_tools/vendor/signtool/`
3. Windows SDK `signtool.exe` (auto-détecté)
4. Certificat auto-signé (fallback)

---

[⬆️ Retour en haut](#-multi-builder-premium)

## ⚡ Démarrage Rapide

### Pour les Pressés (< 5 min) - Installation npm

```powershell
# 1. Installez le builder
npm i metadidomi-builder

# 2. Lancez le builder
npm run build

# 3. C'est tout ! ✅
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**OU directement avec npx :**

```powershell
# 1. Allez dans votre dossier d'application
cd D:\mon-app

# 2. Lancez le builder directement
npx metadidomi-builder

> **À savoir :**

# 3. C'est tout ! ✅
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

### Alternative : Installation Manuelle

```powershell
# 1. Allez dans votre dossier d'application
cd D:\mon-app

# 2. Lancez le builder manuellement
node "C:\chemin-vers\metadidomi-builder\build_tools\builder.js"

# 3. C'est tout ! ✅
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

### Structure Minimale Requise

```
mon-app/
  package.json           ← Généré automatiquement si manquant
  main.js                ← Généré automatiquement si manquant
  index.html             ← Généré automatiquement si manquant
```

**Si aucun fichier n'existe, le builder génère une application de démo complète et fonctionnelle !** 🎉

### ⚠️ Éviter les Boucles Infinies de Build

**IMPORTANT :** Configurez correctement votre `package.json` pour éviter que le builder relance indéfiniment des builds.

#### ❌ MAUVAIS - Créé une boucle infinie

```json
{
  "name": "mon-app",
  "version": "1.0.0",
  "scripts": {
    "start": "npm run build",
    "build": "metadidomi-builder"
  }
}
```

**Problème :** `npm run build` → `metadidomi-builder` → (re)crée `package.json` → retrigger scripts → boucle infinie ❌

#### ✅ BON - Structure Correcte

```json
{
  "name": "mon-app",
  "version": "1.0.0",
  "main": "main.js",
  "scripts": {
    "start": "electron .",
    "dev": "electron .",
    "build": "metadidomi-builder",
    "build:lite": "set LITE_BUILD=true && metadidomi-builder",
    "build:portable": "set CREATE_PORTABLE_EXE=true && metadidomi-builder"
  },
  "dependencies": {
    "electron": "^31.0.0"
  }
}
```

**Points clés :**
- ✅ `"start"` lance **Electron**, pas le builder
- ✅ `"build"` lance **SEULEMENT** le builder
- ✅ Les scripts ne s'appellent pas mutuellement
- ✅ Pas de hook `prestart` ou `postinstall` qui relancerait build
- ✅ Le builder ne modifie PAS le package.json existant

#### 🛡️ Règles de Sécurité pour npm scripts

| Script | Doit faire | Doit PAS faire |
|--------|-----------|---|
| `start` | Lancer Electron ou l'app | Lancer le builder |
| `build` | Lancer le builder | Lancer d'autres scripts |
| `dev` | Mode développement Electron | Lancer le builder |
| `test` | Exécuter les tests | Lancer le builder |

#### 🔒 Protéger votre package.json

**Ne pas ajouter ces hooks dangereux :**

```json
{
  "scripts": {
    "prestart": "npm run build",      // ❌ DANGER: boucle
    "postinstall": "npm run build",   // ❌ DANGER: boucle
    "prepare": "npm run build"        // ❌ DANGER: boucle
  }
}
```

#### ✅ Alternative Sûre : Scripts Séparés

Si vous voulez build ET lancer, créez deux scripts distincts :

```json
{
  "scripts": {
    "build": "metadidomi-builder",
    "start": "electron .",
    "build-and-run": "npm run build && npm start"
  }
}
```

**Utilisation :**
```powershell
npm run build-and-run  # ✅ Build PUIS lance l'app (une fois seulement)
```

---

## 📦 Gestion des Dépendances - Installation et Utilisation

### 🎯 Concept Global

Le système de dépendances du builder fonctionne selon deux niveaux :

1. **Dépendances Electron (npm)** : Pour votre application JavaScript/Electron
2. **Dépendances Python** : Pour les applications Python créées avec le builder Python

#### Architecture Générale

```
Applications Electron           Applications Python
        ↓                               ↓
  package.json              config.py + requirements.txt
        ↓                               ↓
   npm install              pip install (Python Embeddable)
        ↓                               ↓
  node_modules/             Python site-packages/
```

---

## 📌 Installation de Dépendances Electron (JavaScript/Node.js)

### 1️⃣ Ajouter une Dépendance à `package.json`

**Étape 1 : Localiser votre `package.json`**
```powershell
# Depuis votre dossier d'application
ls package.json
```

**Étape 2 : Ajouter une dépendance manuelle (édition directe)**

Ouvrez `package.json` et ajoutez dans la section `"dependencies"` :

```json
{
  "name": "mon-app-electron",
  "version": "1.0.0",
  "main": "main.js",
  "dependencies": {
    "electron-store": "^8.1.0",    // ← Nouvelle dépendance
    "uuid": "^9.0.0",              // ← Nouvelle dépendance
    "axios": "^1.4.0"              // ← Nouvelle dépendance
  }
}
```

**Étape 3 : Installer les dépendances**
```powershell
# Depuis votre dossier d'application
npm install
```

✅ **Résultat :** Tous les packages sont téléchargés dans `node_modules/`

### 2️⃣ Installer via npm directement (Méthode Rapide)

```powershell
# Depuis votre dossier d'application
npm install electron-store uuid axios
```

Cela ajoute automatiquement les dépendances à `package.json` et les installe.

### 3️⃣ Utiliser une Dépendance dans votre Code

Une fois installée, vous pouvez l'importer et l'utiliser :

**Exemple 1 : Utiliser `electron-store` (Stockage persistant)**

```javascript
// main.js ou n'importe quel fichier JavaScript
const Store = require('electron-store');

const store = new Store();

// Stocker une valeur
store.set('user', {
  name: 'Jean',
  email: 'jean@example.com'
});

// Récupérer une valeur
const user = store.get('user');
console.log(user); // { name: 'Jean', email: 'jean@example.com' }
```

**Exemple 2 : Utiliser `uuid` (Générer des IDs uniques)**

```javascript
// renderer.js ou n'importe quel fichier JavaScript
const { v4: uuidv4 } = require('uuid');

// Générer un ID unique
const userId = uuidv4();
console.log(userId); // ex: "550e8400-e29b-41d4-a716-446655440000"
```

**Exemple 3 : Utiliser `axios` (Requêtes HTTP)**

```javascript
// Faire une requête HTTP
const axios = require('axios');

axios.get('https://api.example.com/data')
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    console.error('Erreur:', error);
  });
```

### 4️⃣ Types de Dépendances

| Type | Commande | Description | Utilisation |
|------|----------|-------------|------------|
| **Production** | `npm install package-name` | Code nécessaire à l'exécution | Inclus dans l'app |
| **Développement** | `npm install --save-dev package-name` | Outils de build seulement | Exclu de l'app |

## ✅ Bonnes Pratiques

### 1. Toujours Versionner les Dépendances
```
✅ BON:      package>=1.0.0
❌ MAUVAIS:  package (version flottante)
```

### 2. Tester Localement Avant de Packager
```powershell
# Python - Installation npm
.\node_modules\metadidomi-builder\build_tools\vendor\python_embeddable\python.exe -m pip install -r requirements.txt
python __main__.py

# Python - Installation manuelle
 .\build_tools\vendor\python_embeddable\python.exe -m pip install -r requirements.txt
python __main__.py

# Node/Electron
npm install
npm start
```

### 3. Utiliser `requirements.txt` pour Python
```
# ✅ BON - Fichier requirements.txt
requests==2.31.0
numpy>=1.24.0

# ❌ MAUVAIS - Pas de fichier requirements.txt
```

### 4. Documenter les Dépendances
```python
# En haut de chaque fichier
"""
Dépendances requises:
- requests: pour les appels HTTP
- numpy: pour les calculs numériques
"""
import requests
import numpy as np
```

---

## 🚀 Mise à Jour des Dépendances

### Python

**Installation npm :**
```powershell
# Voir les dépendances qui peuvent être mises à jour
.\node_modules\metadidomi-builder\build_tools\vendor\python_embeddable\python.exe -m pip list --outdated

# Mettre à jour une dépendance
.\node_modules\metadidomi-builder\build_tools\vendor\python_embeddable\python.exe -m pip install --upgrade requests

# Mettre à jour tous les packages
.\node_modules\metadidomi-builder\build_tools\vendor\python_embeddable\python.exe -m pip install --upgrade -r requirements.txt
```

**Installation manuelle :**
```powershell
# Voir les dépendances qui peuvent être mises à jour
 .\build_tools\vendor\python_embeddable\python.exe -m pip  list --outdated

# Mettre à jour une dépendance
 .\build_tools\vendor\python_embeddable\python.exe -m pip install --upgrade requests

# Mettre à jour tous les packages
 .\build_tools\vendor\python_embeddable\python.exe -m pip install --upgrade -r requirements.txt
```

### Node/Electron

```powershell
# Voir les versions disponibles
npm outdated

# Mettre à jour une dépendance
npm update electron-store

# Mettre à jour tout
npm update
```

---

## 🎯 Configuration

### Modes de Construction

#### ⭐ Standard (Défaut) - Installateur NSIS

**Avec npm :**
```powershell
npm run build
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**Avec npx :**
```powershell
npx metadidomi-builder
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**Manuel :**
```powershell
node node_modules/metadidomi-builder/build_tools/builder.js
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

Crée un **installateur professionnel** avec options d'installation, raccourcis, démarrage automatique.

#### 💾 Portable - Exécutable Autonome

**Avec npm :**
```powershell
$env:CREATE_PORTABLE_EXE="true"; npm run build
# Résultat: ./dist/MonApp.exe (portable, ~130MB)
```

**Avec npx :**
```powershell
$env:CREATE_PORTABLE_EXE="true"; npx metadidomi-builder
# Résultat: ./dist/MonApp.exe (portable, ~130MB)
```

Exécutable indépendant sans installation requise.

#### ⚡ LITE - Mode Optimisé

**Avec npm :**
```powershell
$env:LITE_BUILD="true"; npm run build
# Résultat: ./dist/MonApp-Setup-1.0.0.exe (optimisé)
# Rapport: electron-lite-deps-report.txt
```

Analyse et exclut automatiquement les modules inutiles. Génère un rapport détaillé.

#### 🚫 Sans Installateur

**Avec npm :**
```powershell
$env:SKIP_INSTALLER="true"; npm run build
# Résultat: Ressources de base seulement
```

#### 🔐 Avec Chiffrement

**Avec npm :**
```powershell
$env:KEY="votre-clé-secrète"; npm run build
```

**Avec npx :**
```powershell
$env:KEY="votre-clé-secrète"; npx metadidomi-builder
```

#### ⚙️ Compression UPX (Optionnelle)

**Mode rapide (recommandé) :**
```powershell
$env:USE_UPX="true"; npm run build
```

**Mode ultra-brute (très lent, gain maximal) :**
```powershell
$env:USE_UPX="true"; $env:UPX_ULTRA_BRUTE="true"; npm run build
```

#### 🔗 Combinaisons Utiles

**Portable + LITE + Chiffrement :**
```powershell
$env:CREATE_PORTABLE_EXE="true"
$env:LITE_BUILD="true"
$env:KEY="clé-secrète"
npm run build
```

**Avec UPX + Signature personnalisée :**
```powershell
$env:USE_UPX="true"
$env:PFX_PATH="cert.pfx"
$env:PFX_PASS="mot-de-passe"
npm run build
```

### Paramètres Avancés

| Paramètre | Description | Exemple |
|-----------|-------------|---------|
| `--app-src <chemin>` | Dossier source (défaut: cwd) | `--app-src D:\mon-app` |
| `--output <chemin>` | Dossier sortie (défaut: ./dist) | `--output D:\dist` |
| `--out <chemin>` | Alias pour --output | `--out .\dist` |

### Fichiers Automatiquement Exclus

- `node_modules/` - Dépendances (à installer dans votre app)
- `.git/`, `.gitignore` - Version control
- `dist/`, `build/`, `.next/` - Anciens builds et caches
- `.env`, `.env.local` - Variables sensibles
- `*.pem`, `*.key` - Certificats privés

### Temps de Construction

- **Application standard** : 2-3 minutes
- **Avec LITE** : 3-4 minutes (analyse supplémentaire)
- **Avec UPX** : +2-5 minutes selon la taille
- **UPX ultra-brute** : +10-30 minutes

### Fichiers Générés

- `dist/MonApp-Setup-X.X.X.exe` - Installateur NSIS professionnel (défaut)
- `dist/MonApp.exe` - Exécutable portable (si `CREATE_PORTABLE_EXE=true`)
- `dist/MonApp-lite.exe` - Version optimisée (si `LITE_BUILD=true`)
- `electron-lite-deps-report.txt` - Rapport d'optimisation LITE

### Architecture de votre Application

#### Structure Minimale

```
mon-app/
  package.json           ← Obligatoire (généré si manquant)
  main.js                ← Obligatoire (généré si manquant)
  preload.js             ← Optionnel (généré si manquant)
  index.html             ← Optionnel (généré si manquant)
  assets/
    icon.ico             ← Optionnel (utilisé dans l'exe et installateur)
```

#### Structure Recommandée pour Projets Complexes

```
mon-app/
  package.json
  main.js
  preload.js
  index.html
  src/
    components/
    utils/
    renderer.js
  assets/
    icon.ico
    images/
    data/
```

**Important :** Le builder traite **récursivement TOUS les niveaux** de profondeur. Aucune limite !

---

## ✨ Fonctionnalités Principales

- ✅ Construction **100% offline** - Toutes les dépendances embarquées
- ✅ **Reproductibilité** - Builds déterministes et vérifiables
- ✅ **Chiffrement AES-256-CBC** - Ressources protégées
- ✅ **Bytecode V8** - Compilation JavaScript → bytecode natif
- ✅ **Obfuscation intelligente** - Protection du code de fallback
- ✅ **Mode LITE** - Optimisation automatique des dépendances
- ✅ **Compression UPX** - Réduction taille exe (optionnel)
- ✅ **Signature de code** - Automatique ou personnalisée
- ✅ **Exécutable portable** - Sans installation requise
- ✅ **Installateur NSIS** - Interface professionnelle
- ✅ **Validation HMAC** - Intégrité des ressources garantie
- ✅ **Watermarking** - Métadonnées de build sécurisées
- ✅ **Protection preload.js** - Injection auto de sécurité contextBridge
- ✅ **Empaquetage ASAR récursif** - Tous les fichiers inclus à tous les niveaux
- ✅ **Gestion Python** - Support applications Python standalone

# 🛡️ Système de Protection Avancé

Le builder intègre un **système complet de protection du code** avec obfuscation intelligente, chiffrement multi-couches, et anti-analyse.

### Protection pour Electron

**Avec npm :**
```powershell
npm run build -- --light-protection
npm run build -- --medium-protection
npm run build -- --heavy-protection
```

**Avec npx :**
```powershell
npx metadidomi-builder --light-protection
npx metadidomi-builder --medium-protection
npx metadidomi-builder --heavy-protection
```

**Remarque :** Pour les projets Python, voir la section [Protection du Code Python](#niveaux-de-protection-du-code-python).

👉 **[📖 Documentation complète des protections →](node_modules/metadidomi-builder/build_tools_py/PROTECTION_COMMANDS.md)**

## Construction LITE (optimisation)
```powershell
$env:LITE_BUILD="true"
node build_tools/builder.js
```
Génère un rapport `electron-lite-deps-report.txt` avec les modules analysés.

## Clé de Chiffrement Personnalisée
```powershell
$env:KEY="votre-clé-secrète"
npm run build
```
Si non défini, une clé est générée automatiquement.

## Compression UPX
**Mode rapide (défaut) :**
```powershell
$env:USE_UPX="true"
npm run build
```

**Mode ultra-brute (très lent) :**
```powershell
$env:USE_UPX="true"
$env:UPX_ULTRA_BRUTE="true"
npm run build
```

---

## 🏗️ Architecture du Builder

Le builder suit le principe **"zero pollution"** :

- ✅ Répertoire du builder : **jamais modifié**
- ✅ Fichiers générés : dans votre répertoire courant ou `--output`
- ✅ Isolation : utilise uniquement ses outils internes
- ✅ Nettoyage : les fichiers temporaires sont auto-supprimés

### Flux de Compilation

```
Répertoire courant (votre app)
  ↓
  ├─ package.json (généré si manquant)
  ├─ main.js (généré si manquant)
  ├─ index.html (généré si manquant)
  └─ assets/ (créé si manquant)
  ↓
Builder (compile, chiffre, empaque, signe)
  ↓
./dist/
  ├─ MonApp-Setup-1.0.0.exe (installateur)
  └─ MonApp.exe (portable, si demandé)
```

### Fichiers Générés par Défaut

| Fichier | Quand ? | Contenu |
|---------|---------|---------|
| `package.json` | Manquant | Config Electron basique |
| `main.js` | Manquant | Processus principal |
| `preload.js` | Manquant | Bridge sécurisé |
| `index.html` | Manquant | Interface démo |
| `assets/icon.ico` | (optionnel) | Icône de l'app |

---

## 📝 Exemples Pratiques

### Installation npm + Utilisation - Electron

**Exemple 1 : Setup complet avec tous les scripts npm**

```powershell
# Installez le builder
npm i metadidomi-builder

# Ajoutez les scripts à votre package.json:
# "scripts": {
#   "start": "electron .",
#   "dev": "electron .",
#   "build": "metadidomi-builder",
#   "build:lite": "set LITE_BUILD=true && metadidomi-builder",
#   "build:portable": "set CREATE_PORTABLE_EXE=true && metadidomi-builder",
#   "build:encrypted": "set KEY=ma-cle-secrete && metadidomi-builder",
#   "build-and-run": "npm run build && echo Build complete!"
# }

# Puis lancez avec :
npm run build              # Build standard → ./dist/MonApp-Setup-1.0.0.exe
npm run build:lite         # Build optimisé → ./dist/MonApp-Setup-1.0.0.exe
npm run build:portable     # Exécutable portable → ./dist/MonApp.exe
npm run build:encrypted    # Avec clé → ./dist/MonApp-Setup-1.0.0.exe (chiffré)
npm run build-and-run      # Build puis message
```

**Exemple 2 : Avec options avancées dans package.json**

```json
{
  "name": "mon-app-electron",
  "version": "1.0.0",
  "main": "main.js",
  "scripts": {
    "start": "electron .",
    "dev": "electron .",
    "build": "metadidomi-builder",
    "build:lite": "set LITE_BUILD=true && metadidomi-builder",
    "build:portable": "set CREATE_PORTABLE_EXE=true && metadidomi-builder",
    "build:upx": "set USE_UPX=true && metadidomi-builder",
    "build:full": "set CREATE_PORTABLE_EXE=true && set LITE_BUILD=true && set USE_UPX=true && metadidomi-builder",
    "build:protected": "set LITE_BUILD=true && metadidomi-builder --medium-protection"
  },
  "dependencies": {
    "electron": "^31.0.0"
  }
}
```

**Exemple 3 : Avec npx (sans installation)**

```powershell
npx metadidomi-builder                    # Build standard
npx metadidomi-builder --light-protection # Build avec protection légère
npx metadidomi-builder --medium-protection # Build avec protection moyenne
```

npm run build
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**Exemple 2 : Avec npx (sans installation)**
```powershell
npx metadidomi-builder
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**Exemple 3 : Avec options personnalisées**
```powershell
npm run build -- --light-protection
# Ou
$env:KEY="ma-clé-secrète"; npm run build
```

**Exemple 4 : Portable + LITE + UPX**
```powershell
$env:CREATE_PORTABLE_EXE="true"
$env:LITE_BUILD="true"
$env:USE_UPX="true"
node build_tools/builder.js
```

### Dépannage

**Si le build échoue à cause d'un processus Electron bloqué :**
```powershell
taskkill /F /IM electron.exe
```

**Erreur : "Dossier source introuvable"**
- Vérifiez que le chemin `--app-src` existe et est correct

**Erreur EPERM (permissions) :**
- Fermez tous les processus Electron et relancez

---

## 🔒 Protection du Code Source

Le builder utilise une **approche hybride non-destructive** :

### ✅ Fichiers Source Toujours Intacts
- ✅ Vos fichiers originaux ne sont **jamais modifiés**
- ✅ Continuez à éditer votre code après chaque build
- ✅ Chaque build utilise une copie temporaire isolée
- ✅ Cleanup automatique des fichiers temporaires

### 🔄 Traitement Récursif Complet
- ✅ TOUS les fichiers protégés à TOUS les niveaux
- ✅ Aucune limite de profondeur de dossiers
- ✅ Structure complète préservée
- ✅ Même les applications complexes sont totalement protégées

### 🛡️ Couches de Protection

1. **Bytecode V8** - Compilation JavaScript → bytecode natif (résiste à la décompilation)
2. **Fallback Sécurisé** - Code de secours si bytecode échoue
3. **Obfuscation Légère** - Protection additionnelle (compatible et stable)
4. **Chiffrement AES-256** - Ressources et metadata chiffrées
5. **HMAC Validation** - Intégrité vérifiée au lancement

### 🔐 preload.js - Injection Auto-Sécurité

Le builder vérifie et injecte automatiquement la sécurité contextBridge :

```javascript
// ✅ Accepté (déjà sécurisé)
contextBridge.exposeInMainWorld('api', {
  invoke: (channel) => ipcRenderer.invoke(channel)
});

// ⚠️ Sera enrichi par injection auto du builder
contextBridge.exposeInMainWorld('api', {...});
```

L'injection ajoute automatiquement :
- Liste blanche de modules autorisés
- Validation des canaux IPC
- Gestion des erreurs de sécurité

---

### 📊 Protection multiniveau
Cette approche assure :
- ✅ **Sécurité maximale** : Protection forte contre l'analyse statique
- ✅ **Flexibilité** : Vous conservez toujours vos sources
- ✅ **Compatibilité** : Fonctionne sur tous les environnements
- ✅ **Performance** : Bytecode offre optimisation d'exécution
- ✅ **Maintenance** : Facile à mettre à jour et modifier

---

## � Protection Automatique du preload.js - Injection de Sécurité

Le builder inclutt une **protection automatique du preload.js** pour sécuriser l'exposition des APIs. Si votre preload.js n'a pas la protection contextBridge nécessaire, le builder l'injecte automatiquement avant l'empaquetage.

### ✅ Vérification et Injection Automatique

Le builder vérifie à chaque build si votre `preload.js` contient :
1. **contextBridge.exposeInMainWorld** - Exposition sécurisée des APIs
2. **allowedModules** - Liste blanche des modules autorisés
3. **Validation des canaux IPC** - Contrôle d'accès aux communications

### 🔍 Comment ça fonctionne

**AVANT le build :**
```
Vérification du preload.js utilisateur
    ↓
Détecte-t-on contextBridge.exposeInMainWorld ?
    ├─ OUI + allowedModules présent → ✅ Accepté (déjà sécurisé)
    ├─ NON ou allowedModules manquant → Injection nécessaire
    │   ↓
    │   Injection automatique du code de sécurité
    │   ↓
    │   ✅ preload.js sécurisé
    │
    └─ Fichier absent → Création d'un preload.js par défaut sécurisé

Build continue avec preload.js sécurisé → EXE final protégé
```

### 📝 Exemple : preload.js AVANT injection

```javascript
const { contextBridge, ipcRenderer } = require('electron');

// ⚠️ INCOMPLET: Pas de protection
contextBridge.exposeInMainWorld('electron', {
    minimize: () => ipcRenderer.send('minimize-window'),
    maximize: () => ipcRenderer.send('maximize-window'),
    close: () => ipcRenderer.send('close-window')
});
```

**Lors du build, le builder ajoute :**
```javascript
// ... votre code existant ...

// 🔐 SÉCURITÉ AUTO-INJECTÉE: Protection contextBridge
// Cette section a été automatiquement ajoutée par le builder pour sécuriser l'accès aux modules Node.js

// Valider que les modules exposés utilisent une liste blanche
const validateAllowedModules = (name, module) => {
  const ALLOWED_MODULES = {
    'electron': ['ipcRenderer', 'ipcMain', 'app'],
    'path': ['join', 'resolve', 'dirname'],
    'fs': ['readFile', 'writeFile'], // Limiter les accès fs
  };
  
  if (!ALLOWED_MODULES[name]) {
    console.warn(`[SECURITY] Module "${name}" non autorisé dans la liste blanche`);
    return false;
  }
  return true;
};
```

### ✅ preload.js Complet et Sécurisé

Si votre preload.js contient déjà la protection complète, le builder le détecte et **ne double-injecte pas** :

```javascript
const { contextBridge, ipcRenderer } = require('electron');

// 🔐 PROTECTION CONTEXTBRIDGE - Exposer les APIs de manière sécurisée
contextBridge.exposeInMainWorld('api', {
    require: (module) => {
        // 📋 Liste blanche des modules autorisés
        const allowedModules = ['electron', 'path'];
        
        if (allowedModules.includes(module)) {
            return require(module);
        }
        throw new Error(`Module "${module}" non autorisé`);
    }
});
```

**Résultat du build :** ✅ Accepté tel quel, pas de modification

### 📊 Cas Gérés par le Builder

| Cas | Détection | Action | Résultat |
|-----|-----------|--------|----------|
| preload.js absent | ❌ Fichier manquant | ✅ Création auto | preload.js sécurisé créé |
| preload.js incomplet | ⚠️ `contextBridge` présent, `allowedModules` absent | ✅ Injection | Protection ajoutée |
| preload.js complet | ✅ Les deux présents | ✅ Validation | Accepté sans modification |
| preload.js dangereux | ⚠️ Aucune protection détectée | ✅ Injection | Protection complète ajoutée |

### 🛡️ Protection Injectée

Le code injecté fournit :
- **Liste blanche des modules** - Seulement les modules autorisés
- **Validation des canaux IPC** - Seulement les canaux sécurisés
- **Gestion des erreurs** - Messages de sécurité clairs
- **Logging** - Traçabilité des accès refusés

### 🔄 Flux Complet de Build avec Sécurité preload.js

```
┌─────────────────────────────────────┐
│ 1. Vérification du preload.js        │
│    ✅ Détecte contexBridge           │
│    ✅ Valide allowedModules          │
│    ✅ Injection si nécessaire        │
└──────────┬──────────────────────────┘
           │
┌──────────▼──────────────────────────┐
│ 2. Protection Récursive             │
│    ✅ Tous les fichiers protégés     │
│    ✅ À tous les niveaux            │
│    ✅ preload.js inclus             │
└──────────┬──────────────────────────┘
           │
┌──────────▼──────────────────────────┐
│ 3. Empaquetage ASAR Récursif        │
│    ✅ preload.js sécurisé inclus    │
│    ✅ Structure préservée           │
│    ✅ Tous les fichiers présents    │
└──────────┬──────────────────────────┘
           │
┌──────────▼──────────────────────────┐
│ 4. Chiffrement et EXE Final         │
│    ✅ Ressources chiffrées          │
│    ✅ preload.js sécurisé           │
│    ✅ Application protégée          │
└─────────────────────────────────────┘
```

### 💡 Avantages de l'Injection Automatique

- ✅ **Sécurité garantie** : Même si vous oubliez la protection
- ✅ **Pas de modification des sources** : Injection dans la copie temporaire
- ✅ **Flexible** : Respecte votre code existant s'il est complet
- ✅ **Transparent** : Vous ne devez rien faire, c'est automatique
- ✅ **Production-ready** : L'EXE final est sécurisé

### ⚙️ Configuration Optionnelle

Si vous voulez utiliser un preload.js personnalisé sans injection :

```powershell
# Assurez-vous que votre preload.js contient :
# 1. contextBridge.exposeInMainWorld(...)
# 2. Une liste blanche de modules autorisés (allowedModules)
# 3. Une validation des accès

# Puis lancez le builder normalement
node build_tools/builder.js
# → Le builder détecte votre protection et ne fait rien
```

### 📝 Exemple Complet : preload.js Sécurisé

Voici un exemple de preload.js qui sera accepté sans injection :

```javascript
const { contextBridge, ipcRenderer } = require('electron');

// 🔐 LISTE BLANCHE: Modules autorisés
const ALLOWED_MODULES = {
  'electron': ['ipcRenderer', 'ipcMain'],
  'path': ['join', 'resolve'],
};

// 🔐 Valider les accès
const validateModule = (module) => {
  if (!ALLOWED_MODULES[module]) {
    throw new Error(`Module "${module}" non autorisé`);
  }
  return true;
};

// 🔐 Exposer l'API sécurisée
contextBridge.exposeInMainWorld('api', {
  invoke: (channel, data) => {
    const ALLOWED_CHANNELS = ['get-data', 'save-file', 'open-dialog'];
    if (ALLOWED_CHANNELS.includes(channel)) {
      return ipcRenderer.invoke(channel, data);
    }
    throw new Error(`Canal "${channel}" non autorisé`);
  },
  
  require: (module) => {
    validateModule(module);
    return require(module);
  }
});
```

**Résultat du build :** ✅ Accepté directement, aucune modification

---

## �📦 Empaquetage ASAR Récursif - Gestion Complète de la Hiérarchie

Le builder utilise une approche **100% récursive** pour l'empaquetage ASAR, garantissant que **TOUS les fichiers** de votre application, peu importe leur profondeur dans la hiérarchie des dossiers, sont **correctement empaquetés** dans l'archive finale.

### ✅ Traitement Complet de la Structure
L'empaquetage ASAR est **entièrement récursif**, ce qui signifie:
- ✅ Fichiers à la racine : **EMPAQUETÉS**
- ✅ Fichiers dans les sous-dossiers (niveau 1) : **EMPAQUETÉS**
- ✅ Fichiers imbriqués profondément (niveau 2, 3, 4, ...) : **EMPAQUETÉS**
- ✅ Structure complète préservée dans l'archive
- ✅ **Aucune limite de profondeur** - Fonctionne avec n'importe quelle complexité

### 🗂️ Exemple de Structure Complexe
```
Application Source:
├── main.js                              (niveau 0 - racine)
├── preload.js                           (niveau 0 - racine)
├── index.html                           (niveau 0 - racine)
│
├── src/                                 (niveau 1)
│   ├── app.js                          ✅ EMPAQUETÉS
│   ├── config.js
│   │
│   ├── components/                      (niveau 2)
│   │   ├── Button.js                   ✅ EMPAQUETÉS
│   │   ├── Modal.js
│   │   │
│   │   ├── ui/                         (niveau 3)
│   │   │   └── Dialog.js               ✅ EMPAQUETÉS
│   │   │
│   │   └── common/                     (niveau 3)
│   │       ├── Header.js
│   │       ├── Footer.js
│   │       │
│   │       └── layouts/                (niveau 4)
│   │           ├── Main.js             ✅ EMPAQUETÉS (même niveau profond !)
│   │           └── Admin.js
│   │
│   └── utils/                           (niveau 2)
│       ├── helpers.js                  ✅ EMPAQUETÉS
│       ├── validators.js
│       │
│       ├── formatters/                 (niveau 3)
│       │   ├── date.js
│       │   └── number.js               ✅ EMPAQUETÉS
│       │
│       └── common/                     (niveau 3)
│           └── constants.js            ✅ EMPAQUETÉS
│
├── lib/                                 (niveau 1)
│   ├── core.js                         ✅ EMPAQUETÉS
│   ├── engine.js
│   │
│   ├── handlers/                        (niveau 2)
│   │   ├── event.js                    ✅ EMPAQUETÉS
│   │   ├── error.js
│   │   │
│   │   └── middleware/                 (niveau 3)
│   │       ├── auth.js
│   │       ├── cors.js
│   │       │
│   │       └── security/               (niveau 4)
│   │           ├── csrf.js             ✅ EMPAQUETÉS (profond !)
│   │           └── sanitize.js
│   │
│   └── helpers/                         (niveau 2)
│       └── utils.js                    ✅ EMPAQUETÉS
│
└── assets/                              (niveau 1)
    ├── images/                          (niveau 2)
    │   ├── logo.png                    ✅ EMPAQUETÉS
    │   └── icons/                      (niveau 3)
    │       └── app.ico                 ✅ EMPAQUETÉS
    │
    ├── styles/                          (niveau 2)
    │   ├── main.css                    ✅ EMPAQUETÉS
    │   └── themes/                     (niveau 3)
    │       └── dark.css                ✅ EMPAQUETÉS
    │
    └── data/                            (niveau 2)
        └── config/                     (niveau 3)
            └── defaults.json           ✅ EMPAQUETÉS
```

### 🔄 Processus d'Empaquetage Récursif

```
1️⃣ Collecte Récursive
   ├─ Traverse tous les répertoires à TOUS les niveaux
   ├─ Collecte chaque fichier trouvé
   ├─ Respecte les exclusions (node_modules, .git, etc.)
   └─ Résultat: Liste COMPLÈTE de tous les fichiers

2️⃣ Création Archive ASAR
   ├─ Ajoute chaque fichier avec son chemin exact
   ├─ Préserve la hiérarchie complète des dossiers
   ├─ Maintient les permissions et métadonnées
   └─ Résultat: app.asar contient TOUS les fichiers

3️⃣ Chiffrement des Ressources
   ├─ Chiffre app.asar → resources.bin (AES-256-CBC)
   ├─ Stocke métadonnées et clés en sécurité
   └─ Résultat: Ressources protégées dans l'EXE

4️⃣ Création EXE Final
   ├─ Bootstrap déchiffre resources.bin au lancement
   ├─ Valide HMAC et watermark
   ├─ Restaure app.asar en mémoire
   └─ Résultat: Application 100% fonctionnelle
```

### ✅ Garanties d'Intégrité

Le builder garantit que :
1. **Tous les fichiers sont inclus** : 100% des fichiers de votre app, à tous les niveaux
2. **Structure préservée** : Les chemins relatifs et hiérarchie sont intacts
3. **Aucun oubli** : Même les fichiers au niveau 5+ sont traités
4. **Fichiers non protégés exclus** : Les cache, node_modules, .git sont correctement exclus
5. **Taille vérifiée** : app.asar contient exactement la taille attendue

### 🎯 Avantages de l'Approche Récursive

| Aspect | Avantage |
|--------|----------|
| **Complétude** | 100% de l'app est protégée, aucun fichier oublié |
| **Profondeur** | Aucune limite - fonctionne avec n'importe quelle complexité |
| **Flexibilité** | Supporte tous les patterns d'organisation (src, lib, components, etc.) |
| **Performance** | Traversée efficace même pour applications très volumineuses |
| **Fiabilité** | Gestion cohérente de tous les types de fichiers |
| **Maintenance** | Facile d'ajouter du code en profondeur - le builder gère automatiquement |

### 💡 Exemple: Ajout de Nouveaux Fichiers Profonds

Après le premier build, si vous ajoutez de nouveaux fichiers profonds :

```javascript
// Avant: structure existante
src/
  components/
    common/
      Header.js

// Après: vous ajoutez
src/
  components/
    common/
      layouts/
        Main.js  ← Nouveau fichier, niveau 4 !

// Lors du build suivant:
✅ Le builder détecte automatiquement et inclut Main.js
✅ Aucune configuration supplémentaire nécessaire
✅ L'application reste protégée complètement
```

### 🚀 Performance et Optimisation

L'empaquetage récursif est optimisé pour :
- **Vitesse** : Collecte efficace même pour des milliers de fichiers
- **Mémoire** : Traitement par streaming pour gros fichiers
- **Réseau** : 100% offline, aucun téléchargement externe
- **Compatibilité** : Format ASAR standard, compatible Electron

---

## 📊 Comparaison avec electron-builder

| Critère | metadidomi-builder | electron-builder |
|---------|-------------------|------------------|
| **Installation** | 100% offline, vendor local | NPM global ou projet |
| **Dépendances** | Minimal (electron) | Nombreuses |
| **Configuration** | Variables env + builder.js | Config JSON/YAML complexe |
| **Chiffrement ressources** | ✅ AES-256 intégré | ❌ Addon requis |
| **Bytecode protection** | ✅ bytenode natif | ❌ Non |
| **Mode LITE** | ✅ Analyse dépendances | ❌ Non |
| **Build reproducible** | ✅ 100% déterministe | ⚠️ Partiel |
| **Packaging Python** | ✅ Oui (NSIS + obfuscation) | ❌ Non |
| **Protection code Python** | ✅ pyMetadidomi (light/medium/heavy) | ❌ Non |
| **Multi-plateforme** | ⏳ Q1-2026 (macOS/Linux) | ✅ Windows/macOS/Linux |
| **Communauté** | 🆕 En croissance | ✅ Très large |
| **Support commercial** | ✅ Direct (ETS METADIDOMI) | ✅ Communauté + sponsors |

### 🎯 Tableau Récapitulatif

**metadidomi-builder** : 
- ✅ **Sécurité maximale** - AES-256, bytecode, obfuscation pyMetadidomi
- ✅ **100% offline** - Zéro dépendance externe, tous les outils embarqués
- ✅ **Builds reproductibles** - Déterministe et versionnable
- ✅ **Python support** - Applications Python complètes avec installateurs NSIS
- ⏳ **Multi-plateforme** - Windows maintenant, macOS/Linux 2026

**electron-builder** :
- ✅ **Multi-plateforme établi** - Windows, macOS, Linux, AppImage, Snap
- ✅ **Configuration simple** - Template facile à adapter
- ✅ **Communauté large** - Nombreuses extensions et plugins
- ⚠️ **Dépendances réseau** - Nécessite téléchargements externe
- ❌ **Python non supporté** - Electron uniquement

### 💡 Comment Choisir?

**Utilisez metadidomi-builder si vous avez besoin de :**
- 🔐 Sécurité critique (finance, santé, militaire)
- 📦 Packaging Python standalone avec installateur professionnel
- 🔒 Obfuscation et protection du code (anti-reverse engineering)
- 🚫 Environnement complètement offline
- ⚙️ Contrôle total sur le processus de build

**Utilisez electron-builder si vous avez besoin de :**
- 🖥️ Multi-plateforme stable et mature
- 🚀 Setup rapide et facile (peu de configuration)
- 👥 Accès à une large communauté et d'extensions
- 📱 Support macOS/Linux dès maintenant
- 🔄 Updates automatiques intégrées

---

[⬆️ Retour en haut](#-multi-builder-premium)

## � Packaging d'Applications Python

Le builder inclut aussi un **système complet de packaging Python** via `builder.py` pour créer des applications Windows standalone avec installateurs NSIS professionnels.


### 🚀 Démarrage Rapide - Applications Python

**Option 1 : Via npm (Recommandée)**

```powershell
# Installez le builder
npm i metadidomi-builder

# Ajoutez des scripts dans votre package.json
# "scripts": {
#   "build:python": "python node_modules\\metadidomi-builder\\build_tools_py\\builder.py --app-src ./src",
#   "build:python:gui": "python node_modules\\metadidomi-builder\\build_tools_py\\builder.py --gui --app-src ./src"
# }

# Puis lancez avec :
npm run build:python           # Mode console (par défaut)
# Résultat: ./dist/MonApp-Setup-1.0.0.exe

# OU
npm run build:python:gui       # Mode GUI (sans console)
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

> **Astuce :** Placez toujours vos sources Python dans un dossier `src/` et ajoutez l’option `--app-src ./src` à vos scripts dans le `package.json`. Cela évite les erreurs de scan des dossiers `node_modules` et `vendor` (chemins trop longs ou fichiers manquants).
> Placez vos fichiers sources Python (`config.py`, `__main__.py`, etc.) dans un dossier séparé (ex : `src/`).
> Puis lancez le build en précisant le chemin source :
> ```powershell
> npm run build:python -- --app-src ./src
> ```
> Cela évite que le builder scanne le dossier `node_modules` et les vendors, et prévient les erreurs de chemin trop long ou manquant.

**Option 2 : Avec npx (sans installation)**

```powershell
npx metadidomi-builder-python
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**Option 3 : Installation Manuelle**

```powershell
# Mode console simple
node "C:\chemin-vers\metadidomi-builder\build_tools_py\builder.py"
# Résultat: ./dist/MonApp-Setup-1.0.0.exe

# Mode GUI (sans fenêtre console)
node "C:\chemin-vers\metadidomi-builder\build_tools_py\builder.py" --gui
# Résultat: ./dist/MonApp-Setup-1.0.0.exe

# Avec source et sortie personnalisées
node "C:\chemin-vers\metadidomi-builder\build_tools_py\builder.py" --app-src D:\mon-app --output D:\dist --gui
```

### 📋 Structure Minimale d'une Application Python

Le builder détecte automatiquement une application Python valide avec ces fichiers :

```
mon-app-python/
  ├── config.py              ⭐ Configuration (généré si manquant)
  ├── __main__.py            ⭐ Point d'entrée (généré si manquant)
  ├── assets/                ⭐ Ressources (créé si manquant)
  │   └── icon.ico           (optionnel - utilisé dans l'installateur)
  └── ...vos autres fichiers
```

### ✅ Fichiers Requis vs Optionnels

| Fichier | Requis | Description | Auto-généré |
|---------|--------|-------------|-------------|
| `config.py` | ⭐ | Configuration app (nom, version, auteur) | ✅ Oui |
| `__main__.py` | ⭐ | Point d'entrée principal | ✅ Oui |
| `main.py` | ⚠️ | Alternative à `__main__.py` | ✅ Dépistage auto |
| `app.py` | ⚠️ | Alternative au point d'entrée | ✅ Dépistage auto |
| `assets/` | ❌ | Dossier de ressources | ✅ Créé vide |
| `assets/icon.ico` | ❌ | Icône Windows (.ico) | ❌ Non |

**Priority de détection du point d'entrée :** `__main__.py` → `main.py` → `app.py` → `run.py` → `start.py`

### 📝 Exemple : config.py Minimal

```python
# Configuration de l'application
APP_NAME = "MonApp"
VERSION = "1.0.0"
DESCRIPTION = "Application Python"
AUTHOR = "Votre Entreprise"
ENTRY = "__main__"
```

### 📝 Exemple : __main__.py Minimal

```python
#!/usr/bin/env python3
"""
Point d'entrée principal de l'application Python
"""
import sys

def main():
    print("MonApp v1.0.0")
    print("Application Python construite avec Metadidomi Builder")

if __name__ == "__main__":
    main()
```

### 🎨 Applications Python avec Tkinter (Interface Graphique)

Pour les applications avec interface graphique **Tkinter** :

```python
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Application Tkinter avec interface graphique
"""
import tkinter as tk
from tkinter import ttk, messagebox

class MonApp(tk.Tk):
    def __init__(self):
        super().__init__()
        
        self.title("MonApp")
        self.geometry("500x300")
        self.resizable(False, False)
        
        # UI Elements
        label = ttk.Label(self, text="Bienvenue dans MonApp")
        label.pack(pady=20)
        
        button = ttk.Button(self, text="Cliquez-moi", command=self.on_click)
        button.pack(pady=10)
    
    def on_click(self):
        messagebox.showinfo("Info", "Bouton cliqué!")

if __name__ == "__main__":
    app = MonApp()
    app.mainloop()
```

**Builder avec GUI :**
```powershell
# Mode GUI: pas de fenêtre console
python builder.py --gui

# Par défaut: mode console (fenêtre noire reste visible)
python builder.py
```

### 🔍 Auto-Génération Intelligente

Si vous exécutez le builder dans un dossier vide, il génère automatiquement :

```
Dossier vide
    ↓
python builder.py
    ↓
Génération automatique:
  ✅ config.py
  ✅ __main__.py
  ✅ assets/ (dossier vide)
    ↓
  Fichiers de démo prêts à modifier
```

### 🎛️ Modes de Compilation

#### Mode Console (Défaut)
La fenêtre console reste visible quand l'app s'exécute :
```powershell
python builder.py
# ➜ Fenêtre console visible
# ➜ Idéal pour les apps CLI
```

#### Mode GUI
Compile sans fenêtre console (parfait pour les apps Tkinter) :
```powershell
python builder.py --gui
# ➜ Pas de fenêtre console
# ➜ Idéal pour les apps GUI
```

### 🔧 Paramètres Avancés

| Paramètre | Description | Exemple |
|-----------|-------------|---------|
| `--app-src <chemin>` | Dossier source (défaut: cwd) | `--app-src D:\mon-app` |
| `--output <chemin>` | Dossier sortie (défaut: ./dist) | `--output D:\dist` |
| `--out <chemin>` | Alias pour --output | `--out .\dist` |
| `--gui` | Compiler en mode GUI (pas de console) | `--gui` |
| `--no-pyc` | Ne pas compiler les .py en .pyc | `--no-pyc` |
| `--key <clé>` | Clé de chiffrement personnalisée | `--key ma-clé` |

### 💾 Architecture du Packaging Python

Le builder Python utilise une approche **4 étapes** pour protéger et packager votre application :

```
ÉTAPE 1: Archive ZIP Récursive
   ├─ Collecte TOUS les fichiers de l'app
   ├─ À TOUS les niveaux de profondeur
   └─ Crée une archive ZIP chiffrée

ÉTAPE 2: Chiffrement + HMAC
   ├─ Chiffrement Fernet (AES-128)
   ├─ Calcul HMAC-SHA256 (intégrité)
   └─ Bundle sécurisé créé

ÉTAPE 3: Bootstrap d'Auto-Extraction
   ├─ Code Python d'extraction
   ├─ Déchiffrement automatique
   ├─ Validation d'intégrité
   └─ Exécution du code métier

ÉTAPE 4: Compilateur C Launcher
   ├─ Injection dynamique du code Python
   ├─ Compilation du launcher.exe avec GCC
   ├─ Support console ET GUI
   └─ EXE Windows standalone
```

### 🏗️ Flux Complet de Compilation

```
Source Python
    ↓
[Étape 1: Collecte Récursive]
    ├─ Lit tous les fichiers (.py, config, etc.)
    ├─ Exclusion auto: __pycache__, .git, node_modules
    └─ Crée archive.zip chiffrée
    ↓
[Étape 2: Chiffrement Fernet]
    ├─ Chiffre archive.zip → encrypted.bin
    ├─ Calcule HMAC-SHA256
    └─ Clé générée ou personnalisée
    ↓
[Étape 3: Bootstrap Python]
    ├─ Code d'extraction créé
    ├─ Contient clé + HMAC
    └─ Sera injecté dans launcher C
    ↓
[Étape 4: Launcher C + Injection]
    ├─ Code Python injecté dans launcher.c
    ├─ Compilation GCC (console ou GUI)
    ├─ Génère launcher.exe (50-100 KB)
    └─ Minimal et autonome
    ↓
[Étape 5: Installateur NSIS]
    ├─ Bundle launcher.exe + Python Embeddable
    ├─ Crée installateur .exe professionnel
    └─ Sortie: MonApp-Setup-1.0.0.exe
    ↓
Installateur Final
```

### 🐍 Python Embeddable Automatique

Le builder utilise **Python Embeddable** pour les utilisateurs finaux :

- ✅ Python 3.11.9 autonome (64 bits Windows)
- ✅ Pas d'installation système requise
- ✅ Zéro dépendance extérieure
- ✅ Distribution portable
- ✅ Inclus dans l'installateur NSIS

**Localisation :**
- Installation npm : `node_modules/metadidomi-builder/build_tools/vendor/python_embeddable/`
- Installation manuelle : `build_tools/vendor/python_embeddable/`

### 🔐 Protection du Code Python

1. **Compilation en Bytecode** (optionnel)
   - Les fichiers `.py` compilés en `.pyc`
   - Protège contre la lecture directe du source

2. **Chiffrement Fernet**
   - Archive ZIP chiffrée en AES-128
   - Extraction en mémoire à l'exécution
   - Clé générée automatiquement ou personnalisée

3. **Validation HMAC**
   - Vérification d'intégrité des archives
   - Détecte les modifications
   - Arrête l'exécution si compromis

4. **Launcher C Minimaliste**
   - Seulement 50-100 KB
   - Code Python injecté dynamiquement
   - Exécution directe sans interpréteur externe

#### Niveaux de protection du code Python

Vous pouvez protéger votre code Python lors du build avec différents niveaux de protection :

```powershell
npm run build:python -- --app-src ./src --light-protection
npm run build:python:gui -- --app-src ./src --medium-protection
npm run build:python -- --app-src ./src --heavy-protection
```

**Options de protection :**

- `--light-protection` : Obfuscation légère (carbon + junk)
- `--medium-protection` : Obfuscation moyenne (carbon + junk + bugs + dead-code)
- `--heavy-protection` : Protection maximale (toutes les options)

**Remarque :** Ces options ne concernent que les projets Python. Pour Electron, voir la section correspondante.

### 📊 Fichiers Générés

```
dist/
  └── MonApp-Setup-1.0.0.exe     ← Installateur NSIS professionnel
     Contient:
      ├─ launcher.exe             (50-100 KB)
      ├─ Python 3.11.9 Embeddable (35-40 MB)
      ├─ Votre code Python        (chiffré)
      └─ Ressources et assets
```

**Taille finale :** 50-150 MB selon la complexité de l'app

### 🚀 Exemples Complets

#### Exemple 1 : Application Console Simple

**Avec npm :**
```powershell
# Structure
mon-app/
  src/
    config.py
    __main__.py

# Build
cd mon-app
npm run build:python -- --app-src ./src
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

#### Exemple 2 : Application Tkinter GUI

**Avec npm :**
```powershell
# Structure
mon-app-gui/
  src/
    config.py
    __main__.py         ← Interface Tkinter
    assets/
      icon.ico

# Build (mode GUI pour éviter console)
npm run build:python:gui -- --app-src ./src
# Résultat: ./dist/MonApp-Setup-1.0.0.exe (pas de console)
```

#### Exemple 3 : Application Complexe Multi-Fichiers

**Avec npm :**
```powershell
# Structure complexe
mon-app/
  src/
    config.py
    __main__.py
    utils/
      helpers.py
      validators.py
    lib/
      core.py
      handlers/
        events.py
    assets/
      icon.ico
      data.json

# Build
npm run build:python -- --app-src ./src
# TOUS les fichiers récursivement inclus ✅
```

### ⚙️ Options Avancées de Compilation

#### Avec Clé de Chiffrement Personnalisée

**Avec npm :**
```powershell
# Via variable d'environnement
$env:KEY = "ma-clé-secrète-32-caractères"
npm run build:python -- --app-src ./src
```

**Manuel :**
```powershell
# Option 1: Via argument
node build_tools_py/builder.py --app-src ./src --key "ma-clé-secrète-32-caractères"

# Option 2: Via variable d'environnement
$env:KEY = "ma-clé-secrète-32-caractères"
node build_tools_py/builder.py --app-src ./src
```

#### Sans Compilation .pyc

**Avec npm :**

```powershell
npm run build:python -- --no-pyc --app-src ./src
```

**Manuel :**
```powershell
node build_tools_py/builder.py --app-src ./src --no-pyc
# Les .py restent non compilés (plus rapide au build)
```

#### Combinaisons

**Avec npm :**
```powershell
# GUI + clé custom
$env:KEY = "clé-secrète"; npm run build:python:gui -- --app-src ./src
```

**Manuel :**
```powershell
# GUI + clé custom
node build_tools_py/builder.py --app-src ./src --gui --key "clé-secrète"

# Source custom + GUI + sans .pyc
node build_tools_py/builder.py --app-src D:\mon-app --gui --no-pyc
```

### 🔍 Dépistage et Débogage

Le builder affiche un **rapport détaillé** du processus :

```
[builder] 🚀 Metadidomi Python Builder
[builder] Architecture: compatible builder.js (Archive ZIP → Fernet → NSIS)
[builder]
[builder] 📂 Configuration:
[builder]   Source:     D:\mon-app
[builder]   Sortie:     D:\mon-app\dist
[builder]   Temporaire: D:\mon-app\.build-temp
[builder]
[builder] 📋 Informations de l'application:
[builder]   Nom:     MonApp
[builder]   Version: 1.0.0
[builder]   Auteur:  Votre Entreprise
[builder]
[builder] 🔑 Clé de chiffrement auto-générée: a1b2c3d4e5f6...
[builder]
[builder] 🛠️  PHASES DE CONSTRUCTION:
[builder]
[builder] 📦 ÉTAPE 1: Empaquetage récursif...
[builder]   📄 config.py (1.2 KB)
[builder]   📄 __main__.py (2.5 KB)
[builder]   📄 utils/helpers.py (3.1 KB)
[builder]
[builder] ✅ Collecte terminée: 15 fichiers
[builder] 📊 Taille totale: 45.2 MB
```

### 📋 Fichiers Automatiquement Exclus

Le builder **exclut toujours** ces fichiers/dossiers :

- `__pycache__/` - Cache Python compilé
- `.git/`, `.gitignore` - Version control
- `node_modules/` - Dépendances Node (si mixed)
- `dist/`, `build/` - Anciens builds
- `.env`, `.env.local` - Variables sensibles
- `*.pyc`, `*.pyo` - Fichiers compilés
- `config.build.yaml` - Config du builder
- `.build-temp/` - Fichiers temporaires

### ✅ Vérification Post-Build

Après la compilation, vérifiez l'installateur :

```powershell
# Vérifier la présence du fichier
ls dist/
  -Mode     LastWriteTime    Length Name
  -----     ---------------  ------ ----
  -a----    14/11/2025 10:30   85 MB MonApp-Setup-1.0.0.exe

# Installer et tester
.\dist\MonApp-Setup-1.0.0.exe
# → Fenêtre NSIS d'installation
# → Installation dans Program Files
# → Lancement de MonApp
```

### 🐛 Dépannage Courant

**❌ ERREUR: UnicodeDecodeError dans la console PowerShell**
```
UnicodeDecodeError: 'charmap' codec can't decode byte...
```
**✅ SOLUTION:** Le builder force UTF-8 automatiquement. Si toujours problématique :
```powershell
$env:PYTHONIOENCODING = "utf-8"
python builder.py
```

**❌ ERREUR: GCC non trouvé (compilation du launcher)**
```
MinGW64 GCC not found
```
**✅ SOLUTION:** Installez MinGW64 ou modifiez le PATH :
```powershell
# Via chocolatey
choco install mingw
# OU manuellement via https://www.mingw-w64.org/
```

**❌ ERREUR: Python Embeddable non trouvé**
```
Python Embeddable distribution not found
```
**✅ SOLUTION:** Vérifiez le dossier `build_tools/vendor/python_embeddable/`

### 📞 Support

Pour les questions sur le packaging Python :
- 📖 Consultez ce README
- 🐛 Vérifiez les logs du builder
- 💬 Contactez ETS METADIDOMI

---

[⬆️ Retour en haut](#-multi-builder-premium)

## �🗺️ Roadmap - Vision Multi-Plateforme

### Phase 1 : Windows ✅ (Actuelle)
- ✅ Build portable (.exe)
- ✅ Installateur NSIS professionnel
- ✅ Signature de code Windows
- ✅ Protection bytecode + obfuscation
- ✅ Chiffrement AES-256 des ressources
- ✅ Mode LITE d'optimisation
- ✅ 100% offline

### Phase 2 : macOS (Q1 2026)
- Création de DMG et PKG natifs
- Signature de code macOS (codesign)
- Notarization Apple automatique
- Support ARM64 et Intel
- Protection bytecode identique à Windows

### Phase 3 : Linux (Q2 2026)
- Build AppImage et Snap
- Support Debian/RPM
- Packaging cross-distribution
- Protection bytecode uniforme

### Phase 4 : Fonctionnalités Avancées (Q3 2026+)
- Updates automatiques multi-plateforme
- Delta updates (téléchargement optimisé)
- Telemetry anonyme optionnelle
- Support de plugins natifs

---

## 📞 Support et Contribution

**metadidomi-builder** est développé par **ETS METADIDOMI**.

Pour rapporter des bugs, suggérer des features ou contribuer : consultez les guidelines de contribution.

---

