from pydantic import BaseModel
from typing import Optional, List
from datetime import date


#students
class StudentBase(BaseModel):
    name: str
    email: str
    group_name: Optional[str] = None

class StudentCreate(StudentBase):
    pass

class Student(StudentBase):
    id: int

    class Config:
        orm_mode = True


#taechers
class TeacherBase(BaseModel):
    name: str
    department: str

class TeacherCreate(TeacherBase):
    pass

class Teacher(TeacherBase):
    id: int

    class Config:
        orm_mode = True


#courses
class CourseBase(BaseModel):
    title: str
    teacher_id: int

class CourseCreate(CourseBase):
    pass

class Course(CourseBase):
    id: int

    class Config:
        orm_mode = True


#classes
class ClassBase(BaseModel):
    course_id: int
    date: date
    topic: str

class ClassCreate(ClassBase):
    pass

class Class(ClassBase):
    id: int

    class Config:
        orm_mode = True


#grades
class GradeBase(BaseModel):
    student_id: int
    class_id: int
    grade: int

class GradeCreate(GradeBase):
    pass

class Grade(GradeBase):
    id: int

    class Config:
        orm_mode = True
