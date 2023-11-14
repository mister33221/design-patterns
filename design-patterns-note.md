## 甚麼是Design pattern?

![Alt text](image.png)

- 設計模式（Design Patterns）是在軟體工程中常見的解決特定問題的模板。它們是已經被證明有效的解決方案，可以用於解決在設計應用程式或系統時可能遇到的特定設計問題。設計模式可以提高開發人員的效率，因為它們提供了一種可重用和可理解的方式來解決常見問題。
- 設計模式的七大原則
  - 單一職責原則（Single Responsibility Principle, SRP）：一個類應該只有一個改變的原因。這意味著一個類應該只負責一項職責。
    ![Alt text](image-1.png)
  - 開放封閉原則（Open-Closed Principle, OCP）：軟體實體（類、模組、函數等等）應該對擴展開放，對修改封閉。
    ![Alt text](image-2.png)
  - 里氏替換原則（Liskov Substitution Principle, LSP）：如果一個程序使用一個基類的地方，都可以使用其子類，而且不產生任何錯誤或異常，同時保持原有的功能和行為。
    ![Alt text](image-3.png)
    - 範例1: 如果使用以下的程式碼，我的鳥類有麻雀與企鵝，麻雀飛行不會有錯誤，但是企鵝飛行會有錯誤，因此不符合里氏替換原則。
        ```java
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
  - 依賴倒置原則（Dependency Inversion Principle, DIP）：高層模組不應該依賴於低層模組，兩者都應該依賴於抽象。抽象不應該依賴於細節，細節應該依賴於抽象。
  - 介面隔離原則（Interface Segregation Principle, ISP）：客戶端不應該被迫依賴於它不使用的接口。
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
  - 合成/聚合復用原則（Composition/Aggregation Reuse Principle, CARP）：儘量使用對象組合/聚合，而不是繼承來達到軟體復用的目的。
  - 最少知識原則（Principle of Least Knowledge, LoD）：一個對象應該盡可能少地了解其他對象。
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

- 單例模式是一種創建型模式，它提供了一種創建對象的最佳方式。這種模式涉及到單一個類，該類負責創建自己的對象，同時確保只有單個對象被創建。這個類提供了一個方法，可以直接獲取到它的唯一對象。該類創建的對象，稱為單例對象。
- 精神
  - 確保一個類只有一個實例：透過隱藏構造函數並提供一個全局訪問點來實現。
  - 提供一個全局訪問點：透過一個公共的靜態方法來實現，該方法返回該類的唯一實例。
  - 控制對共享資源的存取：透過將這些資源封裝在單例類中來實現。
- 建構方式
    - 懶漢式，指全局的單例實例在第一次被使用時構建。
    - 餓漢式，指全局的單例實例在類加載時構建。
- Example in java
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
  ```