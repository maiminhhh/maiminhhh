# 🍷 Wine Ranking Game (Shiny App)
[![View App](https://img.shields.io/badge/📄%20View%20App-Click%20Here-brightgreen?style=flat-square)](https://maiminhhh.shinyapps.io/WineQuality/)

## Overview

This Shiny app is an interactive **wine-making game** that allows users to simulate their own wine by selecting key chemical properties — and then see how their custom wine compares to real-world samples. Users can choose between **red or white wine**, adjust chemical inputs, and receive a score showing how well their wine ranks relative to others in a reference dataset.

This project was created as part of a group assignment for the **DATA2902** course. I was responsible for the full design and implementation of the Shiny app.

## 🔍 What It Does

- 🎮 **Wine Simulation Game**:
  - Choose between red or white wine.
  - Set values for variables such as:
    - Fixed acidity
    - Residual sugar
    - Density
    - pH
    - Sulphates
    - Total sulfur dioxide
    - Alcohol
    - Volatile acidity
    - …and more.
  - Get a result like: _"Your wine is better than 85% of wines in the dataset!"_

- 📈 **Statistical Insights Tab**:
  - Brief explanation of how scoring is calculated (e.g., using quality distributions).
  - Discussion on data assumptions, limitations, and interpretation of the rankings.

## 🚀 Try It Out

🔗 [Launch the App on Posit Cloud](https://maiminhhh.shinyapps.io/WineQuality/)  

## 🧠 Behind the Scenes

- Dataset: Real-world wine quality dataset (red & white wines)
- Scoring: Based on the predicted or interpolated quality using nearest neighbors or empirical quantiles.
- Interactivity: Built with R Shiny and reactive UI elements.
- Clean and user-friendly interface to engage users in statistical thinking.

## 👩‍💻 My Contributions

- Developed the entire Shiny application (UI + server)
- Designed layout and user interaction logic
- Integrated real dataset and ranking mechanism
- Added a dedicated **"Statistical Background"** tab to explain the analysis

## 🛠 Technologies Used

- **Language**: R
- **Packages**: shiny, tidyverse, DT
- **Deployment**: Posit Cloud
- **Dataset**: Wine Quality Data Set (from UCI Machine Learning Repository)

---

*This project was developed as part of a group submission for the DATA2902 course at the University of Sydney.*
