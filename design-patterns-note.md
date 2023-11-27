## 甚麼是Design pattern?

![Alt text](image.png)

- 設計模式（Design Patterns）是在軟體工程中常見的解決特定問題的模板。它們是已經被證明有效的解決方案，可以用於解決在設計應用程式或系統時可能遇到的特定設計問題。設計模式可以提高開發人員的效率，因為它們提供了一種可重用和可理解的方式來解決常見問題。
- 設計模式的七大原則
  - 單一職責原則（Single Responsibility Principle, SRP）：一個類或方法應該只負責一項職責。
    ![Alt text](image-1.png)
  - 開放封閉原則（Open-Closed Principle, OCP）：軟體實體（類、模組、函數等等）應該對擴展開放，對修改封閉。就是說如果你有需要改變一個類別的行為，你應該藉由擴展(程式碼)，而非修改(程式碼)。
    - 範例1: 如果我想要用這個方法來計算圓形的面積，我必須要修改AreaCalculator的程式碼，這樣就違反了開放封閉原則。
        ```java
        class Rectangle {
            public double width;
            public double height;
        }

        class AreaCalculator {
            public double calculateRectangleArea(Rectangle rectangle) {
                return rectangle.width * rectangle.height;
            }
        }
        ```
    - 範例2: 為了能夠符合開放封閉原則，我們可以建立一個Shape介面，並且讓Rectangle與Circle分別實作這個介面，這樣就可以讓AreaCalculator不需要直接依賴於Rectangle與Circle。
        ```java
        interface Shape {
            double calculateArea();
        }

        class Rectangle implements Shape {
            public double width;
            public double height;

            public double calculateArea() {
                return width * height;
            }
        }

        class Circle implements Shape {
            public double radius;

            public double calculateArea() {
                return Math.PI * Math.pow(radius, 2);
            }
        }

        class AreaCalculator {
            public double calculateShapeArea(Shape shape) {
                return shape.calculateArea();
            }
        }
        ```
    ![Alt text](image-2.png)
  - 里氏替換原則（Liskov Substitution Principle, LSP）：子類別必須能夠替換他們的父類別。也就是說，軟體中的對象應該是可以在不改變程式正確性的前提下被他們的子類別所替換的。
    ![Alt text](image-3.png)
    - 範例1: 如果使用以下的程式碼，我的鳥類有麻雀與企鵝，麻雀飛行不會有錯誤，但是企鵝飛行會有錯誤，因此不符合里氏替換原則。
        ```java
        class Bird {
            void fly() {
                System.out.println("Bird is flying");
            }
        }

        class Penguin extends Bird {
            @Override
            void fly() {
                throw new UnsupportedOperationException("Penguins can't fly");
            }
        }
        ```
    - 範例2: 為了能夠符合里氏替換原則，我們可以將鳥類分為飛行鳥類與不飛行鳥類，並且將企鵝歸類為不飛行鳥類。
        ```java
        class Bird {
        }

        class FlyingBird extends Bird {
            void fly() {
                System.out.println("Bird is flying");
            }
        }

        class Penguin extends Bird {
        }
        ```
  - 介面隔離原則（Interface Segregation Principle, ISP）：子類別不應該被強迫實作他們不會使用的方法。一個類別應該僅有它需要使用的方法，也就是說，介面應該是子類別所需要的，而不是一個臃腫不堪的介面。
    ![Alt text](image-4.png)
    - 範例1: 如果我的worker有一個人類一個機器人，而機器人並不需要eat方法，但是因為worker有eat方法，因此機器人也必須實作eat方法，這樣就違反了介面隔離原則。
        ```java
        interface Worker {
            void work();
            void eat();
        }

        class Human implements Worker {
            @Override
            public void work() {
                System.out.println("Human is working");
            }

            @Override
            public void eat() {
                System.out.println("Human is eating");
            }
        }

        class Robot implements Worker {
            @Override
            public void work() {
                System.out.println("Robot is working");
            }

            @Override
            public void eat() {
                // Do nothing
            }
        }
        ```
    - 範例2: 為了能夠符合介面隔離原則，我們可以將Worker介面拆分為Workable與Feedable兩個介面，並且將Human與Robot分別實作Workable與Feedable兩個介面。
        ```java
        interface Workable {
            void work();
        }

        interface Feedable {
            void eat();
        }

        class Human implements Workable, Feedable {
            @Override
            public void work() {
                System.out.println("Human is working");
            }

            @Override
            public void eat() {
                System.out.println("Human is eating");
            }
        }

        class Robot implements Workable {
            @Override
            public void work() {
                System.out.println("Robot is working");
            }
        }
        ```
  - 依賴倒置原則（Dependency Inversion Principle, DIP）：高層模組不應該依賴於低層模組，兩者都應該依賴於抽象。抽象不應該依賴於細節，細節應該依賴於抽象。
    - 範例1: ElectricPowerSwitch直接依賴於LightBulb，會導致當我們想要使用電燈泡以外的裝置時，必須要修改ElectricPowerSwitch的程式碼，這樣就違反了依賴倒置原則。
        ```java
        // 低層模組
        class LightBulb {}

        // 高層模組
        class ElectricPowerSwitch {
            private LightBulb lightBulb;

            public ElectricPowerSwitch(LightBulb lightBulb) {
                this.lightBulb = lightBulb;
            }

            public void press() {
                // Switch on/off the light bulb
            }
        }
        ```
    - 範例2: 為了遵守依賴倒置原則，我們建立一個interface，並且讓LightBulb與ElectricPowerSwitch分別實作這個interface，這樣就可以讓ElectricPowerSwitch不需要直接依賴於LightBulb。
        ```java
        interface Switchable {
            void turnOn();
            void turnOff();
        }

        class LightBulb implements Switchable {
            public void turnOn() {
                System.out.println("LightBulb: Bulb turned on...");
            }

            public void turnOff() {
                System.out.println("LightBulb: Bulb turned off...");
            }
        }

        class ElectricPowerSwitch {
            private Switchable device;

            public ElectricPowerSwitch(Switchable device) {
                this.device = device;
            }

            public void press() {
                // Switch on/off the device
            }
        }
        ```
        - My experience
          - 實際上，過去的專案中，經常有出現很多類別並沒有interface，因為很多類別並沒有很明確的擴張需求，那我們還需要硬多做一個interface嗎？

  - 合成/聚合復用原則（Composition/Aggregation Reuse Principle, CARP）：儘量使用物件的組合/聚合，而不是繼承來達到軟體復用的目的。
    - 範例1: Car類繼承了Engine類來使用其start方法。這種方式的問題在於，如果Engine類的實現改變，可能會影響到Car類。此外，這種設計也違反了現實世界的模型，因為汽車並不是引擎，汽車有引擎。
    ```java
    class Engine {
        void start() {}
    }

    class Car extends Engine {
        void startCar() {
            start();
        }
    }
    ```
    - 範例2: 為了能夠符合合成/聚合復用原則，我們可以將Engine類的實例作為Car類的成員變數，並且在Car類中調用Engine類的start方法。
    ```java
    class Engine {
        void start() {}
    }

    class Car {
        private Engine engine;

        Car(Engine engine) {
            this.engine = engine;
        }

        void startCar() {
            engine.start();
        }
    }
    ```
  - 最少知識原則（Principle of Least Knowledge, LoD）：一個對象應該盡可能少地了解其他對象。又稱為迪米特原則(Demeter Principle)。核心概念就是降低類別與類別的耦合。
    - 直接朋友: 以參數形式出現在類別中的對象，以成員變數形式出現在類別中的對象，以方法返回值形式出現在類別中的對象。
    - 陌生朋友: 以局部變數形式出現在類別中的對象。
    - 而最少知識原則就試陌生朋友不應該出現在類別中。而盡量變成直接朋友。
    - 範例1: 如果我想要獲取學生的宿舍地址，讓學生類去獲取宿舍地址，但是這樣就違反了最少知識原則，因為學生類並不需要知道宿舍類的存在。
        ```java
        class Car {
            Engine engine;

            Car() {
                engine = new Engine();
            }

            void start() {
                engine.start();
            }
        }

        class Engine {
            void start() {
                // Start the engine
            }
        }

        class Main {
            public static void main(String[] args) {
                Car car = new Car();
                car.engine.start(); // Directly accessing the engine of the car
            }
        }
        ```
    - 範例2: 為了能夠符合最少知識原則，我們可以讓學生類透過宿舍類獲取宿舍地址，這樣就不需要讓學生類知道宿舍類的存在。
        ```java
        class Car {
            private Engine engine;

            Car() {
                engine = new Engine();
            }

            void start() {
                engine.start();
            }
        }

        class Engine {
            void start() {
                // Start the engine
            }
        }

        class Main {
            public static void main(String[] args) {
                Car car = new Car();
                car.start(); // Using the car's start method
            }
        }
        ```
- 設計模式通常可以分為三種類型：
  - 創建型模式（Creational Patterns）：這些模式與對象的創建有關，並且試圖在不指定具體類型的情況下創建對象。
    - 單例模式（Singleton）
    - 工廠方法模式（Factory Method）
    - 抽象工廠模式（Abstract Factory）
    - 建造者模式（Builder）
    - 原型模式（Prototype）
  - 結構型模式（Structural Patterns）：這些模式涉及到類和對象的組合，並且通常用於確保不同的系統部分正確地一起工作。
    - 適配器模式（Adapter）
    - 橋接模式（Bridge）
    - 組合模式（Composite）
    - 裝飾者模式（Decorator）
    - 外觀模式（Facade）
    - 享元模式（Flyweight）
    - 代理模式（Proxy）
  - 行為型模式（Behavioral Patterns）：這些模式專注於對象之間的通信，並且通常用於實現更好的通信和更緊密的關係。
    - 鏈接責任模式（Chain of Responsibility）
    - 命令模式（Command）
    - 解釋器模式（Interpreter）
    - 迭代器模式（Iterator）
    - 中介者模式（Mediator）
    - 備忘錄模式（Memento）
    - 觀察者模式（Observer）
    - 狀態模式（State）
    - 策略模式（Strategy）
    - 模板方法模式（Template Method）
    - 訪問者模式（Visitor）
- 每種設計模式都有其特定的使用場景，並且在適當的情況下使用可以大大提高軟體設計的效率和質量。

### 創建型模式（Creational Patterns）

- 創建型模式涉及到對象的實例化，這類模式都提供一個方法，將客戶從所需要實例化的對象中解耦。

#### 單例模式（Singleton）

- 概述
  - 單例模式是一種創建型模式，它提供了一種創建物件的最佳方式。這種模式涉及到單一個類別，該類別負責創建自己的實例，同時確保只有單個實例被創建。這個類提供了一個方法，可以直接獲取到它的唯一實例。該類別創建的實例，稱為單例對象
- 解決問題
  - 確保一個類別只有一個實例：有些情況下，我們需要確保一個類別只有一個實例存在，例如資料庫連線物件或系統設定物件。單例模式可以確保只有一個實例被建立，並提供一個全域的存取點供其他程式碼使用。
- 解決的方法
  - 私有化建構子：為了確保只有一個實例存在，我們需要將類別的建構子設為私有，這樣其他程式碼就無法直接建立新的實例。
  - 提供一個靜態方法或屬性：為了讓其他程式碼能夠存取該類別的實例，我們需要提供一個靜態方法或屬性，這個方法或屬性可以檢查是否已經有實例存在，如果有就返回該實例，如果沒有就建立一個新的實例並返回。
- 建構方式
  - 餓漢式(靜態常量)
  - 餓漢式(靜態程式碼區塊)
  - 懶漢式(線程不安全)
  - 懶漢式(線程安全，同步方法)
  - 懶漢式(線程安全，同步程式碼區塊)
  - 雙重檢查
  - 靜態內部類
  - 枚舉
- 使用時機
  - 當類只需要一個實例，並且該實例需要提供一個全局訪問點時。例如，如果你需要一個全局的設定對象來存儲應用程式的設定。
  - 當你需要一個共享資源，例如，每個應用程式只能有一個窗口管理器或文件系統。
  - 當你需要一個對象來協調系統中的其他對象。例如，你可能有一個對象來管理所有的數據庫連接。
- 優點
  - 單一實例：確保一個類只有一個實例，並提供一個全局訪問點。
  - 共享資源：單例對象可以被多個其他對象共享，用於存取共享資源。
  - 減少系統開銷：當一個對象的產生需要比較大的系統資源時，有了單例模式，可以避免重複實例化。
- 缺點
  - 全局變量：單例模式實質上就是全局變量，可能會被誤用，導致數據被無意間修改。
  - 測試困難：單例模式會導致代碼之間的緊密耦合，使得單元測試變得困難。
- Example in java
  - 餓漢式(靜態常量): 在類別載入時就完成了實例化，避免了線程同步問題，但是如果從未使用過這個實例，則會造成內存的浪費。
  ```java
    public class Singleton {
        private static final Singleton instance = new Singleton();

        private Singleton() {}

        public static Singleton getInstance() {
            return instance;
        }
    }

    public void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2); // true
    }
  ```
  - 餓漢式(靜態程式碼區塊): 在類別載入時就完成了實例化，避免了線程同步問題，但是如果從未使用過這個實例，則會造成內存的浪費。
  ```java
    public class Singleton {
        private static Singleton instance;

        static {
            instance = new Singleton();
        }

        private Singleton() {}

        public static Singleton getInstance() {
            return instance;
        }
    }

    public void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2); // true
    }
  ```
  - 懶漢式(線程不安全): 在第一次調用getInstance方法時才進行實例化，避免了餓漢式的內存浪費問題，但是如果多個線程同時調用getInstance方法，則可能會產生多個實例。
  ```java
  public class Singleton {
      private static Singleton instance;

      private Singleton() {}

      public static Singleton getInstance() {
          if (instance == null) {
              instance = new Singleton();
          }
          return instance;
      }
  }

    public void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2); // true
    }
  ```
  - 懶漢式(線程安全，同步方法): 在第一次調用getInstance方法時才進行實例化，避免了餓漢式的內存浪費問題，並且使用synchronized關鍵字來保證線程安全，但是每次調用getInstance方法的thread都要排隊，造成效能低落。
  ```java
    public class Singleton {
        private static Singleton instance;
    
        private Singleton() {}
    
        public static synchronized Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }

    public void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2); // true
    }
    ```
  - 懶漢式(線程不安全，同步程式碼區塊): 在第一次調用getInstance方法時才進行實例化，避免了餓漢式的內存浪費問題，並且使用synchronized關鍵字來保證線程安全，但基本上，只要通過if判斷，就算線程安全了，仍然會new出多個實例，因此這種方式並不安全。
  ```java
    public class Singleton {
        private static Singleton instance;

        private Singleton() {}

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    instance = new Singleton();
                }
            }
            return instance;
        }
    }
  ```
  - 雙重檢查(推薦): 在第一次調用getInstance方法時才進行實例化，避免了餓漢式的內存浪費問題，並且使用synchronized關鍵字來保證線程安全，同時也保持了效能，但是需要注意的是，由於指令重排的問題，可能會導致獲取到未初始化完成的實例，因此需要使用volatile關鍵字來保證可見性。
  ```java
    public class Singleton {
        private static volatile Singleton instance;
    
        private Singleton() {}
    
        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
  ```
  - 靜態內部類(推薦使用): 在第一次調用getInstance方法時才進行實例化，避免了餓漢式的內存浪費問題，並且使用靜態內部類來實現延遲實例化，同時藉由JVM的類加載機制來保證線程安全。因為靜態內部類只會被加載一次，因此在加載時，會保證線程安全。
  ```java
    public class Singleton {
        private Singleton() {}
    
        private static class SingletonInstance {
            private static final Singleton INSTANCE = new Singleton();
        }
    
        public static Singleton getInstance() {
            return SingletonInstance.INSTANCE;
        }
    }

    public void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2); // true
    }
  ```
  - 枚舉(推薦): 在第一次調用getInstance方法時才進行實例化，避免了餓漢式的內存浪費問題，並且使用枚舉來實現延遲實例化，同時保證了線程安全。
  ```java
    public enum Singleton {
        INSTANCE;
    }

    public void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance1 == instance2); // true
    }
  ```
  ![Alt text](image-5.png)

#### 簡單工廠模式（Simple Factory）

- 概述
  - 簡單工廠模式是一種創建型模式，它提供了一種將實例化的邏輯封裝在一個方法中的方式。這個方法可以根據不同的參數創建不同的對象。
- 解決問題
  - 當我們需要根據不同的條件或參數來創建不同類型的物件時，可以使用簡單工廠模式來封裝物件的創建邏輯，使客戶端程式碼與具體物件的創建過程解耦。
- 解決的方法
  - 建立一個簡單工廠類別：這個類別負責根據參數的不同來創建不同類型的物件。這個類別通常包含一個靜態方法，根據傳入的參數來創建對應的物件。
  - 客戶端程式碼使用簡單工廠類別：客戶端程式碼需要使用物件時，不直接使用new運算子來創建物件，而是呼叫簡單工廠類別的方法來獲取物件實例。
- 使用時機
  - 當創建對象的邏輯比較複雜，需要將其封裝在一個方法中時。
  - 當需要根據不同的參數創建不同的對象時。
- 優點
  - 簡化了對象的創建：將創建對象的邏輯封裝在一個方法中，使得客戶端不需要知道具體的創建邏輯。
  - 提供了一個全局訪問點：客戶端只需要調用工廠方法就可以獲得所需的對象。
- 缺點
  - 違反了開閉原則：每當需要添加一種新的產品，都需要修改工廠類的代碼。
- Example in java
  - 不符合簡單工廠原則
    ```java
    public interface Product {
    void show();
    }

    public class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct1 show");
        }
    }

    public class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct2 show");
        }
    }

    public class Client {
        public static void main(String[] args) {
            Product product1 = new ConcreteProduct1();
            product1.show();
            Product product2 = new ConcreteProduct2();
            product2.show();
        }
    }
    ```
    - 符合簡單工廠原則
    ```java
    // 建立一個介面
    public interface Product {
        void show();
    }

    // 實現介面的具體產品類
    public class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct1 show");
        }
    }

    public class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct2 show");
        }
    }

    // 建立工廠類
    public class SimpleFactory {
        public Product makeProduct(int kind) {
            switch (kind) {
                case 1:
                    return new ConcreteProduct1();
                case 2:
                    return new ConcreteProduct2();
                default:
                    return null;
            }
        }
    }

    // 客戶端代碼
    public class Client {
        public static void main(String[] args) {
            SimpleFactory factory = new SimpleFactory();
            Product product1 = factory.makeProduct(1);
            if (product1 != null) {
                product1.show();
            }
            Product product2 = factory.makeProduct(2);
            if (product2 != null) {
                product2.show();
            }
        }
    }
    ```

#### 工廠方法模式（Factory Method）

- 概述
  - 工廠方法模式是一種創建型模式，它提供了一種將實例化的邏輯封裝在一個方法中的方式。與簡單工廠模式不同的是，工廠方法模式使用繼承來改變實例化的邏輯，它解決了簡單工廠模式中工廠類職責過重的問題，也遵循了開閉原則。也可以說是當你要製造一個比較複查的產品時，使用簡單工廠會有工廠類職責過重的問題，那麼門就可以使用工廠方法類，有如在工廠中開了不同的產線，讓不同產線去製作不同的產品。
- 解決問題
  - 封裝物件的創建邏輯：當我們需要根據不同的條件或參數來創建不同類型的物件時，可以使用工廠方法模式來封裝物件的創建邏輯，使客戶端程式碼與具體物件的創建過程解耦。
  - 支援擴展：工廠方法模式使得新增新的產品類別變得容易，只需要創建對應的具體工廠類別即可，而不需要修改現有的程式碼。
- 解決方式
  - 定義一個抽象的工廠類別：這個類別定義了一個創建物件的抽象方法，該方法將在具體的工廠類別中實現。
  - 創建具體的工廠類別：這些工廠類別繼承自抽象工廠類別，並實現創建物件的方法。每個具體工廠類別負責創建特定類型的物件。
  - 定義一個抽象的產品類別：這個類別定義了產品的共同屬性和方法，並由具體的產品類別實現。
創建具體的產品類別：這些類別繼承自抽象產品類別，並實現具體的產品功能。
- 使用時機
  - 當一個類不知道它所需要的對象的類時。
  - 當一個類希望由它的子類來指定它所創建的對象時。
  - 當一個類希望將創建對象的責任委託給一個或多個幫助子類，並且你希望將哪個子類是幫助者這一信息局部化時。
- 優點
  - 提供了一種將實例化的邏輯封裝在子類中的方式，這使得客戶端代碼可以與具體類的實例化邏輯解耦。
  - 提供了一種添加新產品的方式，只需要定義一個實現了工廠方法的新類即可。
- 缺點
  - 可能會導致代碼的複雜性增加，因為每增加一種產品，就需要增加一個具體的創建者類。
- Example in java    
  - 符合工廠方法模式
    ```java
    // 建立一個介面
    public interface Product {
        void show();
    }

    // 實現介面的具體產品類
    public class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct1 show");
        }
    }

    public class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct2 show");
        }
    }

    // 建立抽象工廠類
    public abstract class Factory {
        public abstract Product createProduct();
    }

    // 具體工廠類
    public class ConcreteFactory1 extends Factory {
        @Override
        public Product createProduct() {
            return new ConcreteProduct1();
        }
    }

    public class ConcreteFactory2 extends Factory {
        @Override
        public Product createProduct() {
            return new ConcreteProduct2();
        }
    }

    // 客戶端代碼
    public class Client {
        public static void main(String[] args) {
            Factory factory1 = new ConcreteFactory1();
            Product product1 = factory1.createProduct();
            product1.show();

            Factory factory2 = new ConcreteFactory2();
            Product product2 = factory2.createProduct();
            product2.show();
        }
    }    
    ```
#### 抽象工廠模式（Abstract Factory）

- 概述
  - 抽象工廠模式是一種創建型模式，它提供了一種方式，可以將一組具有同一主題的單獨的工廠封裝起來。在抽象工廠模式中，客戶端代碼通過一個共同的介面來創建實際的對象，但是實際創建的對象在程序運行時才會被確定。其實抽象工廠就是簡單工廠+工廠方法。
- 解決問題
  - 封裝物件的創建邏輯：當我們需要創建一組相關或相依的物件時，可以使用抽象工廠模式來封裝物件的創建邏輯，使客戶端程式碼與具體物件的創建過程解耦。
  - 支援產品家族的擴展：抽象工廠模式使得新增新的產品家族變得容易，只需要創建對應的具體工廠類別即可，而不需要修改現有的程式碼。
- 解決方式
  - 定義一個抽象的工廠介面：這個介面定義了創建一組相關或相依物件的方法。
  - 創建具體的工廠類別：這些工廠類別實現了抽象工廠介面，負責創建具體的產品物件。
  - 定義一組抽象的產品介面：這些介面定義了相關或相依產品的共同屬性和方法。
  - 創建具體的產品類別：這些類別實現了抽象產品介面，提供了具體的產品功能。
- 使用時機
  - 當一個系統需要獨立於其產品的創建、組合和表示時。
  - 當一個系統需要配置多個產品系列中的一個產品。
  - 當你想要提供一個產品庫，並且只想顯示它們的接口而不是實現時。
- 優點
  - 提供了一種將實例化的邏輯封裝在具體的工廠類中的方式，這使得客戶端代碼可以與具體類的實例化邏輯解耦。
  - 提供了一種添加新產品的方式，只需要定義一個實現了工廠方法的新類即可。
- 缺點
  - 可能會導致代碼的複雜性增加，因為每增加一種產品，就需要增加一個具體的創建者類。
- Example in java    
    ```java  
    // 建立一個介面
    public interface Product {
        void show();
    }

    // 實現介面的具體產品類
    public class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct1 show");
        }
    }

    public class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("ConcreteProduct2 show");
        }
    }

    // 建立抽象工廠類
    public abstract class AbstractFactory {
        public abstract Product createProduct1();
        public abstract Product createProduct2();
    }

    // 具體工廠類
    public class ConcreteFactory1 extends AbstractFactory {
        @Override
        public Product createProduct1() {
            return new ConcreteProduct1();
        }

        @Override
        public Product createProduct2() {
            return new ConcreteProduct2();
        }
    }

    // 客戶端代碼
    public class Client {
        public static void main(String[] args) {
            AbstractFactory factory = new ConcreteFactory1();
            Product product1 = factory.createProduct1();
            product1.show();
            Product product2 = factory.createProduct2();
            product2.show();
        }
    }
    ```
- My experience
  - 經過的專案其實很少用到抽象類別，不知是們設計的不好，還是其實沒那麼需要

##### Java中有使用到工廠模式的例子嗎？

- java.util.Calendar#getInstance()，細節我就不寫了。

#### 原型模式（Prototype）

- 概述
  - 原型模式是一種創建型模式，它使用複製原型的方式來創建新的對象。在原型模式中，我們可以利用已有的對象創建新的對象，而不需要知道具體的創建細節。
- 解決問題
  - 減少物件的創建成本：有時候創建一個新的物件需要耗費大量的資源和時間，例如從資料庫中讀取大量資料或進行複雜的初始化過程。原型模式可以通過複製現有物件來創建新的物件，避免了重新創建物件的成本。
- 隔離物件的變化：當一個物件的創建過程可能發生變化時，使用原型模式可以將創建邏輯封裝在物件內部，使得客戶端程式碼與具體的創建過程解耦。
- 解決方式
  - 定義一個抽象的原型介面：這個介面定義了一個用於複製物件的方法。
  - 實現原型介面的具體類別：這些類別實現了原型介面，並提供了複製物件的具體實現。
  - 創建原型物件：客戶端程式碼可以通過複製原型物件來創建新的物件，而不需要使用new運算子。
- 使用時機
  - 當一個系統需要獨立於其產品的創建、組合和表示時。
  - 當一個對象的狀態變化達到某一個閾值時，可以將其當作原型進行複製。
- 優點
  - 效能優化：原型模式可以避免創建一個對象需要的昂貴的創建過程（如：資源的初始化、資料庫的讀取等）。
  - 動態添加和删除：原型模式可以在運行時動態地添加或删除產品。
  - 增加或減少產品類：原型模式可以增加或減少產品類。
  - 配置方便：可以在運行時由客戶端動態地指定“克隆體”。
- 缺點
  - 複製複雜對象：對於複雜對象的複製可能會相當困難，特別是當對象之間存在著復雜的引用關係時。
  - 深拷貝與淺拷貝問題：深拷貝與淺拷貝問題是開發人員需要注意的問題，不注意這個問題可能會導致程式行為上的錯誤。
  - 每增加一種產品，就需要增加一個具體的原型類：這會導致代碼的複雜性增加。
- Example in java    
    ```java  
    class Prototype implements Cloneable {
        List<String> list;

        public Prototype() {
            list = new ArrayList<>();
        }

        public Prototype(List<String> list) {
            this.list = list;
        }

        public void addData(String s) {
            list.add(s);
        }

        public List<String> getData() {
            return list;
        }

        // Implementing shallow copy
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        // Implementing deep copy
        public Prototype deepCopy() {
            List<String> newList = new ArrayList<>(this.list);
            return new Prototype(newList);
        }
    }

    public class Main {
        public static void main(String[] args) throws CloneNotSupportedException {
            Prototype prototype = new Prototype();
            prototype.addData("Data 1");
            prototype.addData("Data 2");

            System.out.println("--------------------------");

            Prototype shallowCopy = (Prototype) prototype.clone();
            System.out.println("Prototype list == Shallow copy list: " + (prototype.list == shallowCopy.list));
            System.out.println("--------------------------");

            Prototype deepCopy = prototype.deepCopy();
            System.out.println("Prototype list == Deep copy list: " + (prototype.list == deepCopy.list));
            System.out.println("--------------------------");

            System.out.println("Original object data: " + prototype.getData());
            System.out.println("Shallow copy data: " + shallowCopy.getData());
            System.out.println("Deep copy data: " + deepCopy.getData());
            System.out.println("--------------------------");

            prototype.addData("Data 3");
            System.out.println("Original object data after modification: " + prototype.getData());
            System.out.println("Shallow copy data after original is modified: " + shallowCopy.getData());
            System.out.println("Deep copy data after original is modified: " + deepCopy.getData());
        }
    }
    ```

#### 建造者模式（Builder）

- 概述
  - 主要用於構建複雜對象。它將一個複雜對象的構建與其表示分離，使得同樣的構建過程可以創建不同的表示。建造者模式通常包含以下四個主要元素：
    - 產品（Product）：這是我們想要創建的複雜對象。
    - 建造者（Builder）：這是一個抽象接口，定義了創建產品對象的步驟。
    - 具體建造者（Concrete Builder）：這是實現Builder接口的類，實現了具體的建造過程。
    - 指導者（Director）：這是一個類，它擁有一個Builder對象，並通過這個Builder對象來構建產品。
- 解決問題
  - 封裝物件的建構過程：當一個物件的建構過程非常複雜，包含多個步驟或具有多個可選參數時，可以使用建造者模式將建構過程封裝起來，使得客戶端程式碼不需要知道具體的建構細節。
  - 支援不同的表示：建造者模式可以使用相同的建構過程創建不同的表示，只需在具體的建造者類別中實現不同的建構細節即可。
- 解決方式
  - 定義一個產品類別：這個類別是要建構的物件，包含多個屬性。
  - 定義一個抽象建造者類別：這個類別定義了建構物件的各個步驟，以及獲取建構完成的物件的方法。
  - 創建具體建造者類別：這些類別繼承自抽象建造者類別，實現了建構物件的各個步驟。
  - 創建指揮者類別（可選）：這個類別負責控制建構過程的執行順序，並提供一個方法來獲取建構完成的物件。
  - 客戶端程式碼使用建造者：客戶端程式碼使用具體建造者類別（和指揮者類別，如果有的話）來建構物件，並獲取建構完成的物件。
- 使用時機
  - 當創建複雜對象的算法應該獨立於該對象的組件以及它們的裝配方式時。建造者模式可以提供更好的控制過程。
  - 當構建過程必須允許被構建的對象有不同的表示時。在建造者模式中，使用相同的構建過程可以創建不同的產品。
  - 當需要生成的對象具有複雜的內部結構，這些對象內部的組件之間存在著特定的生成順序。一個簡單的例子可能是創建一個簡單的HTML頁面，也可能是創建複雜的組合模式或者其他複雜的對象。
  - 當要在某個時間點創建一個對象，然後在稍後的時間點再編輯該對象。建造者模式可以提供一種方法來編輯已經創建的對象。
- 優點
  - 封裝性好：客戶端不需要知道具體的創建過程，只需要通過導演類來獲得需要的對象。
  - 擴展性好：如果需要創建的對象有新的類型，只需要擴展一個新的建造者就可以了。
  - 更好的控制細節：建造者模式可以更精細地控制產品的創建過程。例如，產品的創建可能需要多個步驟，這些步驟可能需要在特定的順序中執行。
- 缺點
  - 建造者模式的主要缺點是由於它包含了很多的細節，所以如果建造者內部變化的話，客戶端的代碼也需要進行修改。
  - 建造者模式會導致設計變得更加複雜，因為每個具體的建造者都需要實現相對應的方法來構建產品的各個部分。
- Example in java
  ```java
    public class Product {
        private String partA;
        private String partB;

        public void setPartA(String partA) {
            this.partA = partA;
        }

        public void setPartB(String partB) {
            this.partB = partB;
        }

        public void show() {
            // Show the product
        }
    }

    public abstract class Builder {
        protected Product product = new Product();

        public abstract void buildPartA();
        public abstract void buildPartB();

        public Product getResult() {
            return product;
        }
    }

    public class ConcreteBuilder extends Builder {
        public void buildPartA() {
            product.setPartA("Part A");
        }

        public void buildPartB() {
            product.setPartB("Part B");
        }
    }

    public class Director {
        public void construct(Builder builder) {
            builder.buildPartA();
            builder.buildPartB();
        }
    }

    public class Main {
        public static void main(String[] args) {
            // 指導者
            Director director = new Director();
            // 建造者 = 具體建造者
            Builder builder = new ConcreteBuilder();
            director.construct(builder);
            // 產品
            Product product = builder.getResult();
            product.show();
        }
    }
  ```

### 結構型模式（Structural Patterns）

- 結構型模式涉及到如何組合類和物件以形成更大的結構。結構型模式可以分為類結構型模式和物件結構型模式。類結構型模式使用繼承關係來組合類，物件結構型模式使用物件組合來組合物件。

#### 適配器模式（Adapter）

- 解決問題
  - 主要解決的問題是兩個不相容的接口需要一起工作。例如，你可能有兩個已經存在的類別，它們的接口不相容，但你需要它們一起工作，或者你有一個類別，它的接口需要與多個其他類別的接口相容。
- 解決方法
  - 通過創建一個新的適配器類別，這個類別將一個接口轉換成另一個接口。這個新的適配器類別實現了一個接口，並在其方法實現中使用另一個接口的實例。這樣，適配器類別就將一個接口的調用轉換為另一個接口的調用，使得原本不相容的接口可以一起工作。
- 使用時機
  - 當你想使用一個已經存在的類，但是它的接口不符合你的需求。
  - 當你想創建一個可以與未來未知的類（即那些接口可能不一定兼容的類）協同工作的類。
  - 當你需要使用多個已經存在的子類，但是它們的接口不一致，並且你不能對它們進行修改。
- Example in java
    ```java
    // 假設這是我們已有的、且不能修改的接口
    interface OldInterface {
        void oldMethod();
    }

    // 這是我們已有的實現，我們不能直接使用它
    class OldImplementation implements OldInterface {
        public void oldMethod() {
            System.out.println("Old method");
        }
    }

    // 這是我們期望的新接口
    interface NewInterface {
        void newMethod();
    }

    // 這是我們的適配器，它將新接口轉換為我們可以使用的形式
    class Adapter implements NewInterface {
        private OldInterface oldImplementation;

        public Adapter(OldInterface oldImplementation) {
            this.oldImplementation = oldImplementation;
        }

        public void newMethod() {
            oldImplementation.oldMethod();
        }
    }

    // 這是我們的客戶端代碼，它只知道新接口
    public class Client {
        public static void main(String[] args) {
            OldInterface oldImplementation = new OldImplementation();
            NewInterface adapter = new Adapter(oldImplementation);
            adapter.newMethod();  // 輸出：Old method
        }
    }    
    ```

#### 橋接模式（Bridge）

- 抽象部分與其實現部分分離，使它們可以獨立地變化。這種模式涉及到一個接口作為橋接，使實體類的功能獨立於接口實現類。這兩種類型的類可以按照需求結構化。
- 解決問題
  - 防止一個類別的兩個維度（例如，它的抽象和它的實現）緊密耦合，這會導致這兩個維度的變化都需要修改這個類別。這種情況下，如果這兩個維度的變化都很頻繁，那麼這個類別的維護成本將會非常高。
- 解決方式
  - 將這個類別的抽象和實現分離，並使它們可以獨立變化。這是通過創建兩個獨立的類別層次結構來實現的：一個用於抽象，一個用於實現。抽象類別有一個對實現類別的引用，並通過這個引用來委派實現的工作。
- 使用時機
  - 當你想要避免永久性綁定抽象與實現時，可以使用橋接模式。這種情況可能是因為實現在運行時可能需要被選擇或者切換。
  - 當類的抽象以及它的實現都應該可以通過生成子類來擴展。在這種情況下，橋接模式讓這兩者可以獨立擴展。
  - 當一個類的抽象和實現需要有不同的變化範圍時，橋接模式也很有用。這種情況下，它將抽象和實現分離，使得它們可以各自獨立地變化。
  - 當你有多個繼承樹結構，橋接模式可以用來讓它們解耦，並讓每個繼承樹結構可以獨立地變化。
- Eaxmple in java
  - 將忍者視為抽象部分，而他們的忍術（例如火之術、風之術）視為實現部分。在這種情況下，我們可以使用橋接模式來分離忍者和他們的忍術，使得忍者和忍術可以獨立地變化和擴展。
    ```java
    // 抽象化角色：忍者
    interface Ninja {
        void performJutsu();
    }

    // 實現化角色：忍術
    interface Jutsu {
        void execute();
    }

    // 擴充抽象化角色：火影忍者
    class FireNinja implements Ninja {
        private Jutsu jutsu;

        public FireNinja(Jutsu jutsu) {
            this.jutsu = jutsu;
        }

        public void performJutsu() {
            System.out.print("火影忍者 ");
            jutsu.execute();
        }
    }

    // 具體實現化角色：火之術
    class FireJutsu implements Jutsu {
        public void execute() {
            System.out.println("使用火之術");
        }
    }

    // 具體實現化角色：風之術
    class WindJutsu implements Jutsu {
        public void execute() {
            System.out.println("使用風之術");
        }
    }

    // 客戶端代碼
    public class Client {
        public static void main(String[] args) {
            Jutsu fireJutsu = new FireJutsu();
            Ninja fireNinja = new FireNinja(fireJutsu);
            fireNinja.performJutsu();

            Jutsu windJutsu = new WindJutsu();
            Ninja windNinja = new FireNinja(windJutsu);
            windNinja.performJutsu();
        }
    }
    ```

#### 組合模式（Composite）

- 解決問題
  - 用於解決需要將對象組織成樹形結構以表示"部分-整體"的層次結構的問題。組合模式使得用戶對單個對象和組合對象的使用具有一致性。
- 解決方式
  - 組合模式通過將對象組織成樹形結構（其中每個節點可以是一個單獨的對象或一個包含其他對象的組合對象）來解決這個問題。這種方式使得我們可以對單個對象和組合對象進行一致的操作。
- 組合模式的基本構造
  - Component：組合中的對象聲明接口，在適當的情況下，實現所有類共有接口的默認行為。聲明一個接口用於訪問和管理 Component 的子部件。
  - Leaf：在組合中表示子部件。Leaf 在組合中沒有子部件。
  - Composite：定義有子部件的那些部件的行為。存儲子部件。
- 使用時機
  - 當你需要表示對象的部分-整體層次結構時。組合模式提供了一種方式來組織和操作這種結構。
  - 當你希望客戶端可以忽略組合對象和單個對象之間的差異時。客戶端將對所有對象進行一致的處理。
  - 當你想要在一個統一的抽象類中定義類（包含它們的方法），這些類代表了對象和對象的組合。
- Example in java
  Ninja 是一個接口，它定義了一個方法 showDetails()。IndividualNinja 是實現了 Ninja 接口的類，它是葉節點。NinjaTeam 也是實現了 Ninja 接口的類，但它是一個複合節點，它有一個 Ninja 對象的列表，並在 showDetails() 方法中調用每個 Ninja 的 showDetails() 方法。在客戶端代碼中，我們創建了三個 IndividualNinja 對象和一個 NinjaTeam 對象，然後將 IndividualNinja 對象添加到 NinjaTeam 的忍者列表中，最後調用 NinjaTeam 的 showDetails() 方法。
  ```java
    // Component
    interface Ninja {
        void showDetails();
    }

    // Leaf
    class IndividualNinja implements Ninja {
        private String name;

        public IndividualNinja(String name) {
            this.name = name;
        }

        @Override
        public void showDetails() {
            System.out.println(name);
        }
    }

    // Composite
    class NinjaTeam implements Ninja {
        private List<Ninja> ninjas = new ArrayList<>();

        public void add(Ninja ninja) {
            ninjas.add(ninja);
        }

        public void remove(Ninja ninja) {
            ninjas.remove(ninja);
        }

        @Override
        public void showDetails() {
            for (Ninja ninja : ninjas) {
                ninja.showDetails();
            }
        }
    }

    // Client
    public class Client {
        public static void main(String[] args) {
            Ninja naruto = new IndividualNinja("Naruto");
            Ninja sasuke = new IndividualNinja("Sasuke");
            Ninja sakura = new IndividualNinja("Sakura");

            NinjaTeam team7 = new NinjaTeam();
            team7.add(naruto);
            team7.add(sasuke);
            team7.add(sakura);

            team7.showDetails();
        }
    }  
  ```

####裝飾者模式（Decorator） 

- 解決問題
  - 在不修改現有對象結構的情況下，動態地給一個對象添加一些額外的職責或功能。這種模式提供了比繼承更有彈性的替代方案。
- 解決方式
  - 通過創建一個包裝原有對象的裝飾者對象來解決這個問題。裝飾者對象與原有對象有相同的接口，並且可以在保持對象行為不變的情況下，添加新的行為或責任。
- 裝飾者模式通常包含這些元素
  - Component(抽象組件): 這是我們要動態添加新行為的對象。
  - Decorator(裝飾者): 這是一個包裝器，他包裝了Component，並且可以給他添加溪的行為與責任。
  - ConcreateDecorator(具體裝飾者): 這些對象用於包裝Component，每一個具體裝飾者都可以添加一些新的行為或責任。
- 使用時機
  - 當你需要動態為對象添加職責時：裝飾者模式提供了一種靈活的方式來擴展對象的功能，而不需要創建大量的子類。
  - 當你需要組合行為時：裝飾者模式允許你將行為分解為獨立的對象，然後你可以動態地組合這些對象來實現複雜的行為。
  - 當你需要避免類爆炸時：如果一個類有很多獨立的維度，並且每一個維度都有多個選項，那麼使用繼承來實現這個類可能會導致類的數量爆炸。在這種情況下，你可以使用裝飾者模式來避免類爆炸。
- Example in java: 先創建了一個鳴人對象，然後我們使用影分身和螺旋丸裝飾者來給他添加新的能力。最後，我們打印出鳴人的所有能力。
  ```java
    // 抽象組件：忍者
    public interface Ninja {
        String getAbilities();
    }

    // 具體組件：鳴人
    public class Naruto implements Ninja {
        @Override
        public String getAbilities() {
            return "Naruto: ";
        }
    }

    // 抽象裝飾者：忍術
    public abstract class Jutsu implements Ninja {
        protected Ninja ninja;

        public Jutsu(Ninja ninja) {
            this.ninja = ninja;
        }

        @Override
        public String getAbilities() {
            return ninja.getAbilities();
        }
    }

    // 具體裝飾者：影分身
    public class ShadowClone extends Jutsu {
        public ShadowClone(Ninja ninja) {
            super(ninja);
        }

        @Override
        public String getAbilities() {
            return super.getAbilities() + "Shadow Clone, ";
        }
    }

    // 具體裝飾者：螺旋丸
    public class Rasengan extends Jutsu {
        public Rasengan(Ninja ninja) {
            super(ninja);
        }

        @Override
        public String getAbilities() {
            return super.getAbilities() + "Rasengan, ";
        }
    }

    // 測試
    public class Main {
        public static void main(String[] args) {
            Ninja naruto = new Naruto();
            naruto = new ShadowClone(naruto);
            naruto = new Rasengan(naruto);

            System.out.println(naruto.getAbilities());
        }
    }  
    // result
    // Naruto: Shadow Clone, Rasengan,
  ```
#### 外觀模式（Facade）

- 解決問題
  - 用於為一個複雜的子系統提供一個統一的接口，從而使得子系統更容易使用。這種模式通常用於封裝一個複雜的子系統的細節，並提供一個簡單的接口來進行交互。
- 解決方式
  - 通過創建一個新的外觀類別來解決這個問題，這個外觀類別包含了一個對子系統的引用，並提供一個簡單的接口來進行交互。這種方式使得用戶可以通過外觀類別來使用子系統，而不需要直接與子系統的各個類別和方法進行交互。
- 使用時機
  - 當你需要簡化一個複雜的子系統時，可以使用外觀模式。這種情況可能是因為子系統很難使用或者存在很多的依賴關係。
  - 當你需要將子系統分層時，可以使用外觀模式。使用外觀模式，你可以將子系統分為多個層，每一個層都有自己的外觀類別。
  - 當你需要將子系統封裝起來，以避免將它們暴露給用戶端時，可以使用外觀模式。
  - 當你需要將子系統的組件進行交互時，可以使用外觀模式。外觀模式提供了一個統一的接口，用於與子系統進行交互，並封裝了子系統之間的依賴關係。
- Example in java: 忍者的各種技能（例如：體術、忍術、幻術）視為一個複雜的子系統。我們可以使用外觀模式來提供一個簡單的接口，讓忍者可以輕鬆地使用這些技能。
    ```java
    // 子系統類別：體術
    class Taijutsu {
        void useTaijutsu() {
            System.out.println("Using Taijutsu (Body Techniques)");
        }   
    }
    // 子系統類別：忍術
    class Ninjutsu {
        void useNinjutsu() {
            System.out.println("Using Ninjutsu (Ninja Techniques)");
        }
    }
    // 子系統類別：幻術
    class Genjutsu {
        void useGenjutsu() {
            System.out.println("Using Genjutsu (Illusion Techniques)");
        }
    }
    // 外觀類別：忍者
    class NinjaFacade {
        private Taijutsu taijutsu;
        private Ninjutsu ninjutsu;
        private Genjutsu genjutsu;

        public NinjaFacade() {
            taijutsu = new Taijutsu();
            ninjutsu = new Ninjutsu();
            genjutsu = new Genjutsu();
        }

        public void useTechniques() {
            taijutsu.useTaijutsu();
            ninjutsu.useNinjutsu();
            genjutsu.useGenjutsu();
        }
    }

    public class Client {
        public static void main(String[] args) {
            NinjaFacade ninja = new NinjaFacade();
            ninja.useTechniques();
        }
    }    
    ```

#### 享元模式（Flyweight）

- 解決問題
  - 用於在大量相似對象的情況下，減少記憶體的使用。這種模式通過共享盡可能多的相似對象來達到這個目的。
- 解決方法
  - 通過將對象的狀態分為內部和外部兩部分來解決這個問題。內部狀態是共享的部分，存儲在享元對象中，而外部狀態則由客戶端對象存儲，並在需要時傳遞給享元對象。
- 使用時機
  - 當你需要大量的相似對象時：如果你需要創建大量的相似對象，並且這些對象的一部分狀態可以共享，那麼使用享元模式可以有效地節省記憶體。
  - 當對象的大部分狀態都可以變為外部狀態時：如果一個對象的大部分狀態都可以變為外部狀態，那麼你可以使用享元模式來將這些狀態移出對象，並儲存在外部數據結構中。
  - 當你想要避免高昂的記憶體成本時：如果由於創建大量類似對象而導致記憶體成本過高，那麼使用享元模式可以幫助你減少記憶體的使用。
- Example in java: 將忍術視為一個享元。每種忍術都有一個名稱和類型，這些可以被視為內部狀態。而忍術的使用者和目標則可以被視為外部狀態。
  ```java
    // 享元類別，包含了忍術的內部狀態(名稱與類型)
    class Jutsu {
        private String name;
        private String type;

        public Jutsu(String name, String type) {
            this.name = name;
            this.type = type;
        }
        // 忍術的使用者和目標可以被視為外部狀態
        void useJutsu(String user, String target) {
            System.out.println(user + " uses " + type + " jutsu " + name + " on " + target);
        }
    }
    // 工廠類別，用於創建與共享 Jutsu 物件
    class JutsuFactory {
        private static Map<String, Jutsu> jutsus = new HashMap<>();

        public static Jutsu getJutsu(String name, String type) {
            Jutsu result = jutsus.get(name);
            if (result == null) {
                synchronized (JutsuFactory.class) {
                    if (result == null){
                    result = new Jutsu(name, type);
                    jutsus.put(name, result);
                    }
                }
            }
            return result;
        }
    }
    // 忍者類別，包含忍者名稱與忍術
    class Ninja {
        private Jutsu jutsu;
        private String name;

        public Ninja(String name, Jutsu jutsu) {
            this.name = name;
            this.jutsu = jutsu;
        }

        public void attack(String target) {
            jutsu.useJutsu(name, target);
        }
    }

    public class Client {
        public static void main(String[] args) {
            Jutsu rasengan = JutsuFactory.getJutsu("Rasengan", "A-rank");
            Ninja naruto = new Ninja("Naruto", rasengan);
            naruto.attack("enemy");

            Jutsu chidori = JutsuFactory.getJutsu("Chidori", "A-rank");
            Ninja sasuke = new Ninja("Sasuke", chidori);
            sasuke.attack("enemy");
        }
    }
  ```

#### 代理模式（Proxy）

- 解決問題
  - 用於控制對原始對象的訪問，並允許在對象被訪問前後進行一些處理。
- 解決方式
  - 通過創建一個新的代理類別來解決這個問題，這個代理類別包含了一個對原始對象的引用，並提供與原始對象相同的接口。這種方式使得你可以在客戶端與原始對象之間插入代理對象，從而控制對原始對象的訪問並添加額外的操作。
- 使用時機
  - 當你需要控制對原始對象的訪問時：如果你需要在訪問一個對象時添加一些額外的操作，例如安全檢查、參數驗證、緩存等，那麼你可以使用代理模式。
  - 當你需要隔離客戶端與具體對象之間的耦合時：如果你想要隔離客戶端與具體對象之間的耦合，使得你可以在不影響客戶端的情況下更換或修改對象，那麼你可以使用代理模式。
  - 當你需要實現延遲加載或遠程訪問時：如果一個對象的創建或訪問需要大量的資源，那麼你可以使用代理模式來實現延遲加載或遠程訪問。
- Example in java: 以下是一個基礎的模式，而可以在 ShadowClone clone = new ShadowClone(naruto);後，clone.attack("enemy");前，客製一些操作，如安全驗證、緩存等，這才是使用代理模式的真正目的。
  ```java
    // 忍者接口，定義了忍者的行為
    interface Ninja {
        void attack(String target);

        void checkChakra();
    }

    // 真實的忍者類別，實現了忍者接口
    class RealNinja implements Ninja {
        private String name;
        private int chakra;

        public RealNinja(String name, int chakra) {
            this.name = name;
            this.chakra = chakra;
        }

        // 真實的忍者進行攻擊
        public void attack(String target) {
            System.out.println(name + " attacks " + target);
            chakra -= 10; // 減少 chakra
        }

        // 檢查 chakra 的狀態
        public void checkChakra() {
            System.out.println(name + "'s chakra level: " + chakra);
        }
    }

    // 影分身類別，實現了忍者接口，作為真實忍者的代理
    class ShadowClone implements Ninja {
        private Ninja realNinja;

        public ShadowClone(Ninja realNinja) {
            this.realNinja = realNinja;
        }

        // 影分身進行攻擊，實際上是調用真實忍者的攻擊方法
        public void attack(String target) {
            if(checkChakra() <10){ // 檢查狀態
                System.out.println("Not enough chakra!");
                return;
            } else {
                System.out.println("Shadow clone of " + realNinja + " attacks " + target);
                realNinja.attack(target);
            }
        }

        // 檢查真實忍者的狀態
        public void checkChakra() {
            realNinja.checkChakra();
        }
    }

    public class Client {
        public static void main(String[] args) {
            // 創建一個真實的忍者
            Ninja naruto = new RealNinja("Naruto", 100);
            // 創建一個影分身
            Ninja clone = new ShadowClone(naruto);
            // 讓影分身進行攻擊
            clone.attack("enemy");
        }
    }
  ```

- 


### 行為型模式（Behavioral Patterns）：這些模式專注於對象之間的通信，並且通常用於實現更好的通信和更緊密的關係。
#### 鏈接責任模式（Chain of Responsibility）

- 解決問題
  - 避免請求的發送者和接收者之間的耦合關係。它讓多個對象都有機會處理請求，從而解放出發送者的責任。發送者不需要知道請求是誰處理的，接收者也可以動態地決定是否要處理請求。
- 解決方式
  - 創建一個接口或抽象類別，定義一個方法用於處理請求，並定義一個方法設置下一個處理者。
  - 每個具體的處理者類別實現這個接口或繼承這個抽象類別，並實現處理請求的方法。如果該處理者無法處理請求，則將請求轉發給下一個處理者。
  - 客戶端創建處理者對象，並設置它們的下一個處理者，形成一個鏈條。
  - 客戶端將請求發送到鏈條的第一個處理者，如果該處理者無法處理請求，則將請求轉發給下一個處理者，依此類推。
- 使用時機
  - 當多個對象可以處理同一請求，但具體由哪個對象處理由運行時動態決定時，可以使用鏈接責任模式。這種方式可以避免硬編碼固定的請求處理順序，提高程式的靈活性。
  - 當不明確指定接收者的情況下，向多個對象中的一個提交一個請求時，可以使用鏈接責任模式。請求的發送者不需要知道請求是誰處理的，接收者也可以動態地決定是否要處理請求。
  - 當需要動態指定一組對象處理請求時，可以使用鏈接責任模式。客戶端可以動態地改變處理者鏈條或者動態地改變請求，這提高了程式的靈活性和可擴展性。
  - 當希望用戶可以簡單地發送請求，而不必每次都確定接收者時，可以使用鏈接責任模式。這種方式可以簡化請求的發送者的工作，並讓請求的處理更加靈活。
- Example in java: 將鏈接責任模式應用於忍者的等級系統。假設有一個任務，需要由不同等級的忍者來處理。如果一個忍者無法處理該任務，則將任務傳遞給下一個等級的忍者。這裡的等級可以是：學生、下忍、中忍、上忍和火影。此範例是判斷任務應該要分派給誰，不過責任鏈模式也可以用於處理一個有流程控管的任務。
  ```java
    // 定義一個忍者等級的抽象類別
    abstract class Ninja {
        protected Ninja nextNinja;

        public void setNextNinja(Ninja nextNinja) {
            this.nextNinja = nextNinja;
        }

        public abstract void handleTask(String task);
    }

    // 學生: 忍者等級的最低等級，無法處理任何任務，只能讀書
    class Student extends Ninja {
        public void handleTask(String task) {
            if (task.equals("study")) {
                System.out.println("Student can handle the task: " + task);
            } else if (nextNinja != null) {
                nextNinja.handleTask(task);
            }
        }
    }

    // 下忍: 可以處理 D-rank 任務
    class Genin extends Ninja {
        public void handleTask(String task) {
            if (task.equals("D-rank mission")) {
                System.out.println("Genin can handle the task: " + task);
            } else if (nextNinja != null) {
                nextNinja.handleTask(task);
            }
        }
    }

    // 中忍: 可以處理 C-rank 任務
    class Chunin extends Ninja {
        public void handleTask(String task) {
            if (task.equals("C-rank mission")) {
                System.out.println("Chunin can handle the task: " + task);
            } else if (nextNinja != null) {
                nextNinja.handleTask(task);
            }
        }
    }

    // 上忍: 可以處理 B-rank 任務和 A-rank 任務
    class Jonin extends Ninja {
        public void handleTask(String task) {
            if (task.equals("B-rank mission") || task.equals("A-rank mission")) {
                System.out.println("Jonin can handle the task: " + task);
            } else if (nextNinja != null) {
                nextNinja.handleTask(task);
            }
        }
    }

    // 火影: 可以處理任何任務
    class Hokage extends Ninja {
        public void handleTask(String task) {
            System.out.println("Hokage can handle any task: " + task);
        }
    }

    // 測試
    public class Main {
        public static void main(String[] args) {
            Student student = new Student();
            Genin genin = new Genin();
            Chunin chunin = new Chunin();
            Jonin jonin = new Jonin();
            Hokage hokage = new Hokage();

            student.setNextNinja(genin);
            genin.setNextNinja(chunin);
            chunin.setNextNinja(jonin);
            jonin.setNextNinja(hokage);

            student.handleTask("A-rank mission");
        }
    }
  ```

#### 命令模式（Command）

- 解決問題
  - 用於將操作封裝為一個對象，從而允許你將操作參數化，將操作放入隊列中，將操作保存為日誌，或者支持可撤銷的操作。
- 解決方法
  - 通過將操作封裝為一個對象來解決這個問題。這個對象包含了操作的所有必要信息，例如操作的對象、操作的方法和操作的參數。這種方式使得你可以將操作參數化，並將它們作為一個對象傳遞。此外，你還可以將這些對象放入一個隊列中，並在需要時執行它們。如果這些對象支持撤銷操作，那麼你還可以使用這些對象來實現撤銷功能。
- 使用時機
  - 當你想要將操作參數化，並將它們作為一個對象傳遞時：如果你需要將操作參數化，並將它們作為一個對象傳遞，那麼你可以使用命令模式。這種方式使得你可以在運行時改變一個對象的行為。
  - 當你想要將操作放入隊列中，並在需要時執行它們時：如果你需要將操作放入一個隊列中，並在需要時執行它們，那麼你可以使用命令模式。這種方式使得你可以在適當的時候執行操作，或者將多個操作組合在一起。
  - 當你想要支持撤銷操作時：如果你需要支持撤銷操作，那麼你可以使用命令模式。這種方式使得你可以保存操作的歷史，並在需要時撤銷它們。
- Eample in java: 以進擊的巨人奪回瑪麗雅之牆戰役中，與野獸巨人的的對峙故事為例，我們可以將艾爾文團長的命令視為命令，讓調查兵團(命令的接收者)發動突襲並射出煙霧彈，讓里維兵長(命令的接收者)隻身繞後偷襲野獸巨人。艾爾文團長是調用者，他可以指定調查兵團和里維兵長執行哪個命令。
  ```java
    // 命令接口
    interface Command {
        void execute();
    }

    // 具體的命令：突襲
    class AssaultCommand implements Command {
        private ScoutRegiment scoutRegiment;

        AssaultCommand(ScoutRegiment scoutRegiment) {
            this.scoutRegiment = scoutRegiment;
        }

        public void execute() {
            scoutRegiment.assault();
        }
    }

    // 具體的命令：發射煙霧彈
    class SmokeSignalCommand implements Command {
        private ScoutRegiment scoutRegiment;

        SmokeSignalCommand(ScoutRegiment scoutRegiment) {
            this.scoutRegiment = scoutRegiment;
        }

        public void execute() {
            scoutRegiment.smokeSignal();
        }
    }

    // 具體的命令：繞後突襲
    class FlankAttackCommand implements Command {
        private Levi levi;

        FlankAttackCommand(Levi levi) {
            this.levi = levi;
        }

        public void execute() {
            levi.flankAttack();
        }
    }

    // 接收者：調查兵團
    class ScoutRegiment {
        void assault() {
            System.out.println("Scout Regiment is launching an assault!");
        }

        void smokeSignal() {
            System.out.println("Scout Regiment is firing smoke signals!");
        }
    }

    // 接收者：里維兵長
    class Levi {
        void flankAttack() {
            System.out.println("Levi is launching a flank attack!");
        }
    }

    // 調用者：艾爾文團長
    class Erwin {
        private Command command;

        void setCommand(Command command) {
            this.command = command;
        }

        void executeCommand() {
            command.execute();
        }
    }

    // 客戶端代碼
    public class Main {
        public static void main(String[] args) {
            ScoutRegiment scoutRegiment = new ScoutRegiment();
            Levi levi = new Levi();
            Command assaultCommand = new AssaultCommand(scoutRegiment);
            Command smokeSignalCommand = new SmokeSignalCommand(scoutRegiment);
            Command flankAttackCommand = new FlankAttackCommand(levi);
            Erwin erwin = new Erwin();

            // 艾爾文團長命令調查兵團發動突襲
            erwin.setCommand(assaultCommand);
            erwin.executeCommand();

            // 艾爾文團長命令調查兵團發射煙霧彈
            erwin.setCommand(smokeSignalCommand);
            erwin.executeCommand();

            // 艾爾文團長命令里維兵長繞後突襲
            erwin.setCommand(flankAttackCommand);
            erwin.executeCommand();
        }
    } 
  ```

#### 解釋器模式（Interpreter）

- 解決問題
  - 解決的問題是如何設計一種簡單的語言或者表達式，並且如何解釋和執行這種語言或表達式。這種模式常用於處理頻繁變化的語法或業務規則等場景。
- 解決方式
  - 定義一個語法樹（Syntax Tree），每個節點表示一個語法規則。這個語法樹是由具體的解釋器對象構成的。
  - 定義一個解釋器接口或抽象類別，定義解釋操作。
  - 每個具體的解釋器類別實現解釋器接口或繼承解釋器抽象類別，並實現解釋操作。解釋操作通常是遞歸的，每個解釋器只解釋自己對應的語法規則，並調用其子節點的解釋操作。
  - 客戶端創建語法樹，並調用解釋操作。
- 使用時機
  - 當有一個語言需要解釋執行，並且可以將該語言中的句子表示為一個抽象語法樹時，可以使用解釋器模式。這種情況常見於編譯器的設計和實現。
  - 當一個簡單的語言需要頻繁的變化時，可以使用解釋器模式。這種情況常見於業務規則的處理，例如規則引擎。
  - 當一些重複發生的問題可以用一種簡單的語言來進行表達時，可以使用解釋器模式。這種情況常見於SQL解析、符號處理等。
- 缺點
  - 這種方式可以將語法規則和解釋操作解耦，使得語法規則的變化不會影響到解釋操作，並且可以方便地添加新的語法規則和解釋操作。但是，解釋器模式的缺點是對於複雜的語法規則，解釋器的設計和實現可能會變得很複雜。
- Example in java: 這個例子實現了一個簡單的加法和減法的解釋器
  ```java
    // 定義一個表達式的接口
    interface Expression {
        int interpret();
    }

    // 定義一個數字表達式
    class NumberExpression implements Expression {
        private int number;

        public NumberExpression(int number) {
            this.number = number;
        }

        public int interpret() {
            return this.number;
        }
    }

    // 定義一個加法表達式
    class AddExpression implements Expression {
        private Expression expr1;
        private Expression expr2;

        public AddExpression(Expression expr1, Expression expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        public int interpret() {
            return this.expr1.interpret() + this.expr2.interpret();
        }
    }

    // 定義一個減法表達式
    class SubtractExpression implements Expression {
        private Expression expr1;
        private Expression expr2;

        public SubtractExpression(Expression expr1, Expression expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        public int interpret() {
            return this.expr1.interpret() - this.expr2.interpret();
        }
    }

    // 測試
    public class Main {
        public static void main(String[] args) {
            Expression expr = new AddExpression(
                new NumberExpression(1), 
                new SubtractExpression(
                    new NumberExpression(2), 
                    new NumberExpression(1)
                )
            );
            System.out.println("The result is: " + expr.interpret());
        }
    }  
  ```

#### 迭代器模式（Iterator）



#### 中介者模式（Mediator）



#### 備忘錄模式（Memento）



#### 觀察者模式（Observer）



#### 狀態模式（State）



#### 策略模式（Strategy）



#### 模板方法模式（Template Method）



#### 訪問者模式（Visitor）






class(類別)是用來定義object(物件)
一個object就是某個class的instance(實例)


![Alt text](image-6.png)


```java
// State Pattern
interface State {
    void transform(Character character);
}

class Human implements State {
    @Override
    public void transform(Character character) {
        character.setState(new Titan());
        character.setAttackStrategy(new TitanAttack());
    }
}

class Titan implements State {
    @Override
    public void transform(Character character) {
        character.setState(new Human());
        character.setAttackStrategy(new HumanAttack());
    }
}

// Strategy Pattern
interface AttackStrategy {
    void attack();
}

class HumanAttack implements AttackStrategy {
    @Override
    public void attack() {
        System.out.println("Human is attacking!");
    }
}

class TitanAttack implements AttackStrategy {
    @Override
    public void attack() {
        System.out.println("Titan is attacking!");
    }
}

// Character class
class Character {
    private State state;
    private AttackStrategy attackStrategy;

    public Character(State state, AttackStrategy attackStrategy) {
        this.state = state;
        this.attackStrategy = attackStrategy;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void transform() {
        state.transform(this);
    }

    public void attack() {
        attackStrategy.attack();
    }
}
```