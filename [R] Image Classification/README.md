# 🧠 Tumour vs. Immune Cell Classification from H&E Images

[![View Final Report](https://img.shields.io/badge/📄%20View%20Report-Click%20Here-brightgreen?style=flat-square)](https://maiminhhh.github.io/maiminhhh/data3888-2/)


This project benchmarks multiple machine learning models to classify **tumour** vs. **immune** cells from H&E-stained image patches using engineered image features. Completed as part of **DATA3888: Data Science Capstone** at the University of Sydney.

---

## 📂 Overview

- 📊 Dataset: 1,976 labelled image patches
- 🧪 Task: Binary classification — *Tumour* vs. *Immune*
- 🛠️ Language: **R**
- 📦 Libraries: `tidyverse`, `caret`, `ggplot2`, `EBImage`, `e1071`, `randomForest`

---

## 🔁 Workflow

### 📦 1. Data Processing
- Manual inspection and cleaning of 1,976 image patches
- Stratified **80/20 train-test split**
- Balanced classes and removed 4 blank samples

### 🧮 2. Feature Engineering
- Extracted image descriptors:
  - **Histogram of Oriented Gradients (HOG)**
  - **Histogram of Colours (HSV bins)**

### 🤖 3. Models Evaluated
- **Random Forest**
- **Support Vector Machine (SVM)**
- **K-Nearest Neighbours (KNN)**
- **Convolutional Neural Network (CNN)** *(exploratory)*

---

## 📊 Evaluation Metrics

| Metric | Description |
|--------|-------------|
| Accuracy | Correct predictions over all samples |
| Precision | % of predicted tumours that are actual tumours |
| Recall | % of true tumours that were correctly identified |
| F1 Score | Harmonic mean of precision and recall |

---

## ⚠️ Limitations

- CNN performance limited by small dataset and training time
- Manual hyperparameter tuning due to compute constraints
- Additional domain knowledge and histopathology expertise would benefit feature selection

---

## 🔮 Future Work

- Incorporate more complex image features (e.g., Gabor filters, texture)
- Experiment with transfer learning from pre-trained CNNs
- Evaluate ensemble methods and calibration techniques

---

## 📁 Repo Structure

| File | Description |
|------|-------------|
| `100/` | Saved image features (HOG, colour histograms) |
| `presentation.pdf` | Final summary slides |
| `report.qmd` / `.html` | Full project report with results and discussion |

---

## 🧰 Tools & Technologies

- **Language**: R  
- **Packages**: `tidyverse`, `caret`, `EBImage`, `e1071`, `randomForest`, `ggplot2`  
- **Workflow**: Modular R scripts with reproducible pipeline logic

---

## 🧠 Author

Developed by **Mai Minh**  
As part of **DATA3888: Data Science Capstone**  
The University of Sydney – Semester 1, 2025

---

## 📄 License

This project is for educational and academic use only. Please contact the author before reusing the dataset or model outputs.

