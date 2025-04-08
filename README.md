# 🧹 Exploratory Data Analysis & Data Cleaning  
### *Australian Health Survey (AHS) Project | Job Application Submission*

---

## 🚀 Overview

This project demonstrates my ability to clean, structure, and explore complex real-world data. The dataset — a subset of the **Australian Health Survey (AHS)** — simulates challenges often encountered in industry: messy data, inconsistent formats, special missing value logic, and metadata embedded in external files.

As part of the interdisciplinary research workflow, my task was to:
- **Clean and validate** multiple datasets
- **Integrate disparate sources** using subject and household keys
- **Diagnose and document missingness**
- Deliver technically sound data structures for future analysis

---

## 📦 Dataset Summary

| File                    | Description                            |
|-------------------------|----------------------------------------|
| `AHS11biomedical.csv`   | Biomedical indicators                  |
| `AHS11nutrient.csv`     | Nutritional intake                     |
| `AHS11food.csv`         | Two-day food diary per participant     |
| `nutmstatDataItems2019.xlsx` | Data dictionary & metadata        |

**Key Identifiers:**
- `ABSPID`: Unique person ID (primary key across all datasets)
- `ABSHID`: Household ID (for capturing within-household dependencies)

---

## 🔍 Cleaning & Processing Approach

Using **R**, I engineered a modular cleaning pipeline that ensures:
- Accurate type inference using metadata from Excel sheets
- Consistent handling of **special codes for missingness**
- Clear documentation of every transformation for reproducibility

The cleaned version of the dataset is stored in `clean_data.Rdata`
