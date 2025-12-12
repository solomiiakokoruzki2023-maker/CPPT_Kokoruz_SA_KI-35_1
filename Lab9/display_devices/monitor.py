"""
Модуль для базового класу Монітор.
"""

class Monitor:
    """
    Базовий клас, що представляє загальний комп'ютерний монітор.

    Атрибути:
        manufacturer (str): Виробник монітора.
        model (str): Модель монітора.
        resolution (str): Максимальна роздільна здатність (наприклад, '1920x1080').
        diagonal_inches (float): Діагональ екрана в дюймах.
    """

    def __init__(self, manufacturer: str, model: str, resolution: str, diagonal_inches: float):
        """
        Конструктор класу Monitor.
        """
        self.manufacturer = manufacturer
        self.model = model
        self.resolution = resolution
        self.diagonal_inches = diagonal_inches
        self.is_powered_on = False

    def power_on(self):
        """
        Вмикає монітор.
        """
        if not self.is_powered_on:
            self.is_powered_on = True
            print(f"Монітор {self.manufacturer} {self.model} увімкнено.")
        else:
            print(f"Монітор {self.model} вже увімкнений.")

    def power_off(self):
        """
        Вимикає монітор.
        """
        if self.is_powered_on:
            self.is_powered_on = False
            print(f"Монітор {self.manufacturer} {self.model} вимкнено.")
        else:
            print(f"Монітор {self.model} вже вимкнений.")

    def get_info(self) -> str:
        """
        Повертає рядок з основною інформацією про монітор.
        """
        status = "увімкнений" if self.is_powered_on else "вимкнений"
        return (f"Тип пристрою: Монітор\n"
                f"  Виробник: {self.manufacturer}\n"
                f"  Модель: {self.model}\n"
                f"  Роздільна здатність: {self.resolution}\n"
                f"  Діагональ: {self.diagonal_inches}\"\n"
                f"  Статус: {status}")