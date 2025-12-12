"""
Головний модуль (точка входу в програму).
Демонструє створення та використання об'єктів базового та похідного класів.
"""

# Імпортуємо класи з нашого пакета
from display_devices.monitor import Monitor
from display_devices.touchscreen import Touchscreen

def main():
    """
    Основна функція програми.
    """
    print("--- 💻 Демонстрація класу Monitor (Базовий клас) ---")

    # Створення об'єкта базового класу
    standard_monitor = Monitor(
        manufacturer="LG",
        model="27GN800-B",
        resolution="2560x1440",
        diagonal_inches=27.0
    )

    # Виклик методів базового класу
    print(standard_monitor.get_info())
    standard_monitor.power_on()
    print("-" * 20)
    print(standard_monitor.get_info())
    print("-" * 50)


    print("--- 📱 Демонстрація класу Touchscreen (Похідний клас) ---")

    # Створення об'єкта похідного класу
    interactive_display = Touchscreen(
        manufacturer="Samsung",
        model="Flip 3",
        resolution="3840x2160",
        diagonal_inches=55.0,
        multitouch_points=20,
        stylus_support=True
    )

    # Виклик успадкованих методів (power_on)
    interactive_display.power_on()
    print("\nВиклик перевизначеного методу get_info():")
    print(interactive_display.get_info())
    print("-" * 20)

    # Виклик нових методів похідного класу
    interactive_display.process_touch(2)     # Валідний дотик
    interactive_display.process_touch(25)    # Забагато дотиків
    print("-" * 20)

    # Демонстрація роботи у вимкненому стані
    interactive_display.power_off()
    interactive_display.process_touch(1)
    print("-" * 50)

if __name__ == "__main__":
    # Точка входу: запуск основної функції
    main()