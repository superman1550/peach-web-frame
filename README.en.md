# Peach Web Frame

Peach Web Frame is a lightweight Java-based web application framework providing rich UI components, a data access layer, and an event handling mechanism.

## Features

- **Rich UI Components**: Built-in various web interface components, including text fields, combo boxes, tree controls, data tables, calendars, etc.
- **Data Access Layer**: Complete DAO architecture supporting database CRUD operations.
- **Event-Driven**: Comprehensive event listening mechanism supporting actions, selections, changes, and other events.
- **Layout Management**: Flexible window layout system supporting five-region layout: north, south, east, west, and center.
- **Configuration Management**: Convenient configuration file reading and system parameter management.

## Project Structure

```
cn/jpeach/frame/
├── action/          # Event listeners
├── cache/           # Cache management
├── comp/            # UI components
├── conf/            # Configuration management
├── core/            # Core framework
├── dao/             # Data access layer
├── entity/          # Entity classes
├── exception/       # Exception definitions
└── util/            # Utility classes
```

## Quick Start

### Prerequisites

- JDK 8+
- Maven 3.x

### Build the Project

```bash
mvn clean install
```

### Configure Data Source

Configure the database connection information in `config.xml`:

```xml
```