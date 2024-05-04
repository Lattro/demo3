package com.example.demo.models;
import com.example.demo.helpers.DoctorEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book_list")
@Data
public class BookModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "doctor")
    @Enumerated
    DoctorEnum doctorEnum;
    @Column(name = "time")
    String time;

    @Column(name = "tg_id")
    String tgId;
}
