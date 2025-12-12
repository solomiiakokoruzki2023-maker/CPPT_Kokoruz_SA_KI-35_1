"""
Модуль для похідного класу Сенсорний екран.
Успадковує від базового класу Monitor.
"""

# Імпортуємо базовий клас
from .monitor import Monitor

class Touchscreen(Monitor):
    """
    Похідний клас, що представляє сенсорний екран.
    Успадковує всі властивості та методи від класу Monitor і додає сенсорний функціонал.

    Додаткові атрибути:
        multitouch_points (int): Максимальна кількість одночасних точок дотику.
        stylus_support (bool): Чи підтримує екран активний стилус.
    """

    def __init__(self, manufacturer: str, model: str, resolution: str, diagonal_inches: float,
                 multitouch_points: int, stylus_support: bool = False):
        """
        Конструктор класу Touchscreen.
        Викликає конструктор базового класу (Monitor) і додає власні атрибути.
        """
        # Виклик конструктора базового класу
        super().__init__(manufacturer, model, resolution, diagonal_inches)
        self.multitouch_points = multitouch_points
        self.stylus_support = stylus_support

    def process_touch(self, num_touches: int):
        """
        Імітує обробку дотику або жестів.
        """
        if self.is_powered_on:
            if num_touches > 0 and num_touches <= self.multitouch_points:
                print(f"Сенсорний екран {self.model}: Обробка {num_touches} дотику/жесту.")
            elif num_touches > self.multitouch_points:
                 print(f"Сенсорний екран {self.model}: Забагато дотиків ({num_touches}). Максимум: {self.multitouch_points}.")
            else:
                 print(f"Сенсорний екран {self.model}: Очікування вводу.")
        else:
            print(f"Сенсорний екран {self.model} вимкнений. Неможливо обробити дотик.")

    def get_info(self) -> str:
        """
        Перевизначає метод get_info() базового класу, додаючи інформацію про сенсорні можливості.
        """
        # Отримуємо основну інформацію від базового класу
        monitor_info = super().get_info()
        stylus_status = "Так" if self.stylus_support else "Ні"

        # Додаємо специфічну інформацію
        return (f"{monitor_info}\n"
                f"  Тип: Сенсорний екран\n"
                f"  Мультитач (макс. точок): {self.multitouch_points}\n"
                f"  Підтримка стилуса: {stylus_status}")