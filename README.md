# 🚀Multi-Builder premium

**Builder nouvelle génération** avec chiffrement de ressources, protection bytecode, obfuscation intelligente et prise en charge Python. 
**Windows maintenant, macOS et Linux en développement.**

---
 ![](/docs/icon.ico) 
---
### 💝 Soutenir le projet

Si ce builder vous a aidé, merci de soutenir le développement :

[![Sponsor Lygos](https://img.shields.io/badge/Sponsor-Lygos-blue?style=for-the-badge)](https://pay.lygosapp.com/$etsmeta)
![Version](https://img.shields.io/badge/version-1.1.171125-blue)
![License](https://img.shields.io/badge/license-MIT-green)
![Stars](https://img.shields.io/github/stars/METADIDOMIOFFICIEL/Metadidomi-Builder?style=social)
![Issues](https://img.shields.io/github/issues/[METADIDOMIOFFICIEL/Metadidomi-BUILDER])

---
## 📑 Table des Matières

1. **[À Propos](#-à-propos)** - Présentation générale
2. **[Installation](#-installation)** - Mise en place
3. **[Démarrage Rapide](#-démarrage-rapide)** - Premiers pas
4. **[Configuration](#-configuration)** - Personnalisation
5. **[Modes de Construction](#-modes-de-construction)** - Options de build
6. **[Gestion des Dépendances](#-gestion-des-dépendances)** - Electron et Python
7. **[Protection du Code](#-système-de-protection-avancé)** - Sécurité
8. **[Packaging Python](#-packaging-dapplications-python)** - Applications Python
9. **[Comparaison](#-comparaison-avec-electron-builder)** - vs electron-builder
10. **[Roadmap](#-roadmap)** - Futures versions
11. **[Support](#-support-et-contribution)** - Aide et contact

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

### Dépendances Minimales

**Pour le développement :** Seuls `electron@^39.1.1` et Python 3.11+ sont requis.

```powershell
npm install
```
# � INSTALLATION DES DÉPENDANCES VENDOR

Si lors de l'installation le dossier `build_tools/vendor` n'est pas présent, suivez ces instructions :

. Téléchargez le fichier `vendor.zip` depuis :
  https://github.com/METADIDOMIOFFICIEL/Metadidomi-Builder/releases/download/1.3.171125/vendor.zip

💡 **Suite** :
Vous pouvez aussi extraire manuellement le contenu de `vendor.zip` dans le dossier `build_tools/vendor`.

### Modules Embarqués - 100% Offline

Toutes les dépendances essentielles sont incluses dans `build_tools/vendor/` :

```
build_tools/vendor/
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
- 📁 Stocké dans `build_tools/certs/cert-[hash].pfx`
- 🔑 Mot de passe dans `build_tools/certs/cert-[hash].key`
- ⚡ Aucune configuration requise

#### Mode 2 : Certificat Personnalisé (Production)

```powershell
# Option 1 : Fichier dans le dossier par défaut
# Placer: build_tools/certs/signing.pfx

# Option 2 : Variables d'environnement
$env:PFX_PATH="chemin/vers/certificat.pfx"
$env:PFX_PASS="mot-de-passe-certificat"

# Lancer le build
node build_tools/builder.js
```

**Priorité de signature :**
1. Module personnalisé `build_tools/signing.js` (si présent)
2. `signtool.exe` local dans `build_tools/vendor/signtool/`
3. Windows SDK `signtool.exe` (auto-détecté)
4. Certificat auto-signé (fallback)

---

## ⚡ Démarrage Rapide

### Pour les Pressés (< 5 min)

```powershell
# 1. Allez dans votre dossier d'application
cd D:\mon-app

# 2. Lancez le builder
node D:\chemin-vers\metadidomi-builder\build_tools\builder.js

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
# Python
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

```powershell
node build_tools/builder.js
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

Crée un **installateur professionnel** avec options d'installation, raccourcis, démarrage automatique.

#### 💾 Portable - Exécutable Autonome

```powershell
$env:CREATE_PORTABLE_EXE="true"
node build_tools/builder.js
# Résultat: ./dist/MonApp.exe (portable, ~130MB)
```

Exécutable indépendant sans installation requise.

#### ⚡ LITE - Mode Optimisé

```powershell
$env:LITE_BUILD="true"
node build_tools/builder.js
# Résultat: ./dist/MonApp-Setup-1.0.0.exe (optimisé)
# Rapport: electron-lite-deps-report.txt
```

Analyse et exclut automatiquement les modules inutiles. Génère un rapport détaillé.

#### 🚫 Sans Installateur

```powershell
$env:SKIP_INSTALLER="true"
node build_tools/builder.js
# Résultat: Ressources de base seulement
```

#### 🔐 Avec Chiffrement

```powershell
$env:KEY="votre-clé-secrète"
node build_tools/builder.js
```

#### ⚙️ Compression UPX (Optionnelle)

```powershell
# Mode rapide (recommandé)
$env:USE_UPX="true"
node build_tools/builder.js

# Mode ultra-brute (très lent, gain maximal)
$env:USE_UPX="true"
$env:UPX_ULTRA_BRUTE="true"
node build_tools/builder.js
```

#### 🔗 Combinaisons Utiles

```powershell
# Portable + LITE + Chiffrement
$env:CREATE_PORTABLE_EXE="true"
$env:LITE_BUILD="true"
$env:KEY="clé-secrète"
node build_tools/builder.js

# Avec UPX + Signature personnalisée
$env:USE_UPX="true"
$env:PFX_PATH="cert.pfx"
$env:PFX_PASS="mot-de-passe"
node build_tools/builder.js
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

### Deux Modes de Protection

**Mode Interactif** - Questions guidées pour chaque protection
```powershell
cd metadidomi-builder/build_tools_py/pyMetadidomi
python pyMetadidomi.py
```

**Mode CLI** - Automatisé avec arguments
```powershell
# Presets rapides
python pyMetadidomi.py script.py --light-protection      # Léger
python pyMetadidomi.py script.py --medium-protection     # Moyen
python pyMetadidomi.py script.py --heavy-protection      # Maximal

# Options individuelles
python pyMetadidomi.py script.py --carbon                 # Obfuscation
python pyMetadidomi.py script.py --junk                   # Code parasite
python pyMetadidomi.py script.py --anti-vm                # Anti-VM
python pyMetadidomi.py script.py --multi-encrypt          # Chiffrement multi-couches
```

### Protection pour Electron

```powershell
node build_tools/builder.js --light-protection
node build_tools/builder.js --medium-protection
node build_tools/builder.js --heavy-protection
```

👉 **[📖 Documentation complète des protections →](build_tools_py/PROTECTION_COMMANDS.md)**

## Construction LITE (optimisation)
```powershell
$env:LITE_BUILD="true"
node build_tools/builder.js
```
Génère un rapport `electron-lite-deps-report.txt` avec les modules analysés.

## Clé de Chiffrement Personnalisée
```powershell
$env:KEY="votre-clé-secrète"
node build_tools/builder.js
```
Si non défini, une clé est générée automatiquement.

## Compression UPX
```powershell
# Mode rapide (défaut)
$env:USE_UPX="true"
node build_tools/builder.js

# Mode ultra-brute (très lent)
$env:USE_UPX="true"
$env:UPX_ULTRA_BRUTE="true"
node build_tools/builder.js
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

```powershell
# Mode simple (depuis votre app)
cd D:\MonApp
node C:\metadidomi-builder\build_tools\builder.js

# Avec source et sortie personnalisées
node build_tools/builder.js --app-src D:\mon-app --output D:\dist

# Avec chiffrement
$env:KEY="ma-clé-secrète"
node build_tools/builder.js --app-src C:\MonApp

# Portable + LITE + UPX
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

## 📊 Comparaison avec electron-builder
- Options d'obfuscation sûres et compatibles
- Pas de transformation agressive du code
- Préserve la stabilité de l'application

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
| **Build reproducible** | ✅ Oui | ⚠️ Partiel |

**metadidomi-builder** : Optimal pour sécurité maximale, 100% offline, protection bytecode
**electron-builder** : Optimal pour multi-plateforme, configuration simple, communauté large

👉 **Choisir metadidomi-builder si** : sécurité critique, environnement offline, control total
👉 **Choisir electron-builder si** : multi-plateforme, setup simple, app standard

---

## � Packaging d'Applications Python

Le builder inclut aussi un **système complet de packaging Python** via `builder.py` pour créer des applications Windows standalone avec installateurs NSIS professionnels.

### 🚀 Démarrage Rapide - Applications Python

**Le plus simple** - Exécutez le builder Python depuis votre dossier d'application :

```powershell
# Depuis votre répertoire d'application Python
cd D:\mon-app-python
python D:\chemin-vers\metadidomi-builder\build_tools_py\builder.py

# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

**Ou avec options personnalisées :**

```powershell
# Source et sortie personnalisées
python builder.py --app-src D:\mon-app --output D:\dist

# Mode GUI (sans fenêtre console)
python builder.py --gui

# Combiné
python builder.py --app-src D:\mon-app --output D:\dist --gui
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

**Localisation :** `build_tools/vendor/python_embeddable/`

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

```powershell
# Structure
mon-app/
  config.py
  __main__.py

# Build
cd mon-app
python ..\builder.py
# Résultat: ./dist/MonApp-Setup-1.0.0.exe
```

#### Exemple 2 : Application Tkinter GUI

```powershell
# Structure
mon-app-gui/
  config.py
  __main__.py         ← Interface Tkinter
  assets/
    icon.ico

# Build (mode GUI pour éviter console)
cd mon-app-gui
python ..\..\builder.py --gui
# Résultat: ./dist/MonApp-Setup-1.0.0.exe (pas de console)
```

#### Exemple 3 : Application Complexe Multi-Fichiers

```powershell
# Structure complexe
mon-app/
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

# Build avec sortie personnalisée
python builder.py --app-src D:\mon-app --output D:\dist
# TOUS les fichiers récursivement inclus ✅
```

### ⚙️ Options Avancées de Compilation

#### Avec Clé de Chiffrement Personnalisée

```powershell
# Option 1: Via argument
python builder.py --key "ma-clé-secrète-32-caractères"

# Option 2: Via variable d'environnement
$env:KEY = "ma-clé-secrète-32-caractères"
python builder.py
```

#### Sans Compilation .pyc

```powershell
python builder.py --no-pyc
# Les .py restent non compilés (plus rapide au build)
```

#### Combinaisons

```powershell
# GUI + sortie personnalisée + clé custom
python builder.py --gui --output D:\dist --key "clé-secrète"

# Source custom + GUI + sans .pyc
python builder.py --app-src D:\mon-app --gui --no-pyc
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
