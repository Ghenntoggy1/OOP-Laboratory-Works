import matplotlib.pyplot as plt
import numpy as np
import sympy as sp
import math
class amam:
class Function:
    def __init__(self):
        self.x = sp.symbols('x')
        self.func = sp.exp(self.x - sp.pi) + sp.cos(self.x) - self.x + sp.pi
        self.f_func = sp.lambdify(self.x, self.func, modules=['numpy'])

    def plot_function(self):
        xpoints = np.linspace(0, 6, num=200)
        ypoints = self.f_func(xpoints)
        plt.plot(xpoints, ypoints, label=f'{self.func}')
        plt.legend(loc='upper center', shadow=True)
        plt.grid('both')
        plt.show()

class NewtonMethod:
    def __init__(self, function, epsilon, x0):
        self.function = function
        self.epsilon = epsilon
        self.x0 = x0
        self.err = []

    def find_root(self):
        xn = self.x0
        for j in range(1, 1001):
            fxn = self.function.f_func(xn)
            dfxn = self.function.deq_func(xn)
            xn1 = xn - fxn / dfxn
            self.err.append(abs(xn1 - xn))
            if abs(xn1 - xn) < self.epsilon:
                return xn1, j
            xn = xn1

    def find_multiplicity(self):
        m = 1
        fpot = self.function.func
        if self.function.f_func(np.pi) == 0:
            while sp.lambdify(self.function.x, self.function.deriv(fpot), modules=['numpy'])(np.pi) < self.epsilon:
                fpot = self.function.deriv(fpot)
                m += 1
        return m

    def find_order_of_convergence(self, m):
        if m == 1:
            return "Linear Convergence"
        return "Non-Linear Convergence"

def main():
    function = Function()
    function.plot_function()

    epsilon = 0.000001
    xi0 = 0
    newton = NewtonMethod(function, epsilon, xi0)
    root, iterations = newton.find_root()
    m = newton.find_multiplicity()
    convergence = newton.find_order_of_convergence(m)

    print("Root =", root)
    print("Iterations =", iterations)
    print("Multiplicity =", m)
    print("Convergence =", convergence)

if __name__ == "__main__":
    main()
