from sqlalchemy.orm import Session
from . import models, schemas


#students

def get_student(db: Session, student_id: int):
    return db.query(models.Student).filter(models.Student.id == student_id).first()


def get_students(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.Student).offset(skip).limit(limit).all()


def create_student(db: Session, student: schemas.StudentCreate):
    db_student = models.Student(
        name=student.name,
        email=student.email,
        group_name=student.group_name
    )
    db.add(db_student)
    db.commit()
    db.refresh(db_student)
    return db_student


def delete_student(db: Session, student_id: int):
    student = db.query(models.Student).filter(models.Student.id == student_id).first()
    if student:
        db.delete(student)
        db.commit()
    return student


def update_student(db: Session, student_id: int, updated_data: schemas.StudentCreate):
    student = db.query(models.Student).filter(models.Student.id == student_id).first()
    if student:
        student.name = updated_data.name
        student.email = updated_data.email
        student.group_name = updated_data.group_name
        db.commit()
        db.refresh(student)
    return student


#courses

def get_course(db: Session, course_id: int):
    return db.query(models.Course).filter(models.Course.id == course_id).first()


def get_courses(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.Course).offset(skip).limit(limit).all()


def create_course(db: Session, course: schemas.CourseCreate):
    db_course = models.Course(
        title=course.title,
        teacher_id=course.teacher_id
    )
    db.add(db_course)
    db.commit()
    db.refresh(db_course)
    return db_course


def delete_course(db: Session, course_id: int):
    course = db.query(models.Course).filter(models.Course.id == course_id).first()
    if course:
        db.delete(course)
        db.commit()
    return course


def update_course(db: Session, course_id: int, updated_data: schemas.CourseCreate):
    course = db.query(models.Course).filter(models.Course.id == course_id).first()
    if course:
        course.title = updated_data.title
        course.teacher_id = updated_data.teacher_id
        db.commit()
        db.refresh(course)
    return course


#grades

def get_grade(db: Session, grade_id: int):
    return db.query(models.Grade).filter(models.Grade.id == grade_id).first()


def get_grades(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.Grade).offset(skip).limit(limit).all()


def create_grade(db: Session, grade: schemas.GradeCreate):
    db_grade = models.Grade(
        student_id=grade.student_id,
        class_id=grade.class_id,
        grade=grade.grade
    )
    db.add(db_grade)
    db.commit()
    db.refresh(db_grade)
    return db_grade


def delete_grade(db: Session, grade_id: int):
    grade = db.query(models.Grade).filter(models.Grade.id == grade_id).first()
    if grade:
        db.delete(grade)
        db.commit()
    return grade


def update_grade(db: Session, grade_id: int, updated_data: schemas.GradeCreate):
    grade = db.query(models.Grade).filter(models.Grade.id == grade_id).first()
    if grade:
        grade.student_id = updated_data.student_id
        grade.class_id = updated_data.class_id
        grade.grade = updated_data.grade
        db.commit()
        db.refresh(grade)
    return grade
