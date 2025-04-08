# 📊 Project: Analysis of Bustling Regions in Greater Sydney

![Project Logo](https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Logo_of_the_University_of_Sydney.svg/120px-Logo_of_the_University_of_Sydney.svg.png)

## 📍 Dataset Description

### 🚏 **SA2 Regions**
- **Data Source**: The Australian Bureau of Statistics (ABS). You can access it [here](https://www.abs.gov.au/).
- **Purpose**: Provides the geographical baseline for analysis, including region codes, names, and geometry.
- **Preprocessing**: Filtered to include only regions within the Greater Sydney area.

### 🚌 **Stops**
- **Data Source**: Transport Open Data Hub. Download [here](https://opendata.transport.nsw.gov.au/dataset/timetables-complete-gtfs).
- **Purpose**: Public transportation data helps analyze region accessibility and commuting frequency.
- **Preprocessing**: Longitude and latitude values are used to calculate geometry points.

### 🗳️ **Polling Places**
- **Data Source**: Australian Electoral Commission (AEC). Access [here](https://results.aec.gov.au/24310/Website/HouseDownloadsMenu-24310-Csv.htm).
- **Purpose**: Indicates electoral activity, an indicator of regional 'bustling'.
- **Preprocessing**: Filtered to include only NSW polling places. Missing geometry inferred from suburb.

### 🏫 **Schools**
- **Data Source**: NSW Department of Education. Access [here](https://data.cese.nsw.gov.au/data/dataset/school-intake-zones-catchment-areas-for-nsw-government-schools).
- **Purpose**: Measures youth engagement, a key factor in bustling regions.
- **Preprocessing**: Only current primary and secondary schools are included.

### 👥 **Population**
- **Data Source**: Provided by DATA2X01 cohort at The University of Sydney via Canvas.
- **Purpose**: Scales other facilities by the region's population density to refine bustling scores.
- **Preprocessing**: Population counts recalculated, with a specific focus on youth (0-19 years).

### 💵 **Income**
- **Data Source**: DATA2X01 cohort dataset, available via Canvas.
- **Purpose**: Analyzes potential correlation between income and bustling levels.
- **Preprocessing**: Converted median income from string to integer format.

### 🚍 **Additional Public Transport**
- **Data Source**: Australian Government. Access [here](https://data.gov.au/dataset/ds-nsw-1311d015-a1ea-427f-b2c7-f161063719c9/details?q=).
- **Purpose**: Adds other transport modes to the analysis, enriching the bustling metric.
- **Preprocessing**: Transformed transport modes into a wide format for easier counting.

### 🏞️ **Additional Recreational Facilities**
- **Data Source**: NSW Government. Access [here](https://portal.spatial.nsw.gov.au/portal/home/item.html?id=a761e34d6be04bf6ac133d204e7c0d44).
- **Purpose**: Includes tourist attractions and facilities promoting engagement in activities.
- **Preprocessing**: Filtered to include only entries relevant to both residents and tourists.

### 🚻 **National Public Toilets**
- **Data Source**: NSW Government. Access [here](https://portal.spatial.nsw.gov.au/portal/apps/sites/#/homepage).
- **Purpose**: Public toilets reflect an area's commitment to outdoor activities, thus impacting the bustling level.
- **Preprocessing**: Calculated geometry from longitude and latitude for each entry.

## 🗃️ **Database Schema**

The following diagram illustrates the relationships between datasets used in this analysis:

![Database Diagram](dbdiagram.png)

## 🔢 **Score Analysis**

### 📝 **Score Formula**:
The bustling score for each region is calculated using the following formula:

\[
\text{Score} = S(z_{\text{business}} + z_{\text{transports}} + z_{\text{polls}} + z_{\text{schools}} + z_{\text{recreations}} + z_{\text{toilets}})
\]

Where:
- \( z \): Normalized z-score for each category.
- \( S \): Sigmoid function applied to normalize the overall score.

### 💡 **Score Calculation**:
- A temp table for each category is created.
- Spatial join performed to map the data with corresponding regions.
- Normalization applied using \( \frac{\text{quantity}}{\text{area}} \).
- Z-scores calculated and the final bustling score derived.

### 🔍 **Outlier Handling**:
- Median and IQR applied for outlier detection in bustling scores.
