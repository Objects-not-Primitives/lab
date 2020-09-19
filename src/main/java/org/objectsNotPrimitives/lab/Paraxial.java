package org.objectsNotPrimitives.lab;

public class Paraxial {
    double m_F;
    double m_F_;
    double m_SF;
    double m_SF_;
    double m_SH;
    double m_SH_;

    public Paraxial(double m_F, double m_F_, double m_SF, double m_SF_, double m_SH, double m_SH_) {
        this.m_F = m_F;
        this.m_F_ = m_F_;
        this.m_SF = m_SF;
        this.m_SF_ = m_SF_;
        this.m_SH = m_SH;
        this.m_SH_ = m_SH_;
    }

    @Override
    public String toString() {
        return "Paraxial: \n" +
                "F = " + m_F +
                ",\nF' = " + m_F_ +
                ",\nSF = " + m_SF +
                ",\nSF' = " + m_SF_ +
                ",\nSH = " + m_SH +
                ",\nSH' = " + m_SH_;
    }
}
