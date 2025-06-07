from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from typing import List

from .. import models, schemas, crud, database

router = APIRouter()

def get_db():
    db = database.SessionLocal()
    try:
        yield db
    finally:
        db.close()



@router.post("/", response_model=schemas.Grade)
def create_grade(grade: schemas.GradeCreate, db: Session = Depends(get_db)):
    return crud.create_grade(db=db, grade=grade)


@router.get("/", response_model=List[schemas.Grade])
def read_grades(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    return crud.get_grades(db, skip=skip, limit=limit)


@router.get("/{grade_id}", response_model=schemas.Grade)
def read_grade(grade_id: int, db: Session = Depends(get_db)):
    db_grade = crud.get_grade(db, grade_id=grade_id)
    if db_grade is None:
        raise HTTPException(status_code=404, detail="Grade not found")
    return db_grade


@router.delete("/{grade_id}", response_model=schemas.Grade)
def delete_grade(grade_id: int, db: Session = Depends(get_db)):
    grade = crud.delete_grade(db, grade_id)
    if grade is None:
        raise HTTPException(status_code=404, detail="Grade not found")
    return grade


@router.put("/{grade_id}", response_model=schemas.Grade)
def update_grade(grade_id: int, grade: schemas.GradeCreate, db: Session = Depends(get_db)):
    updated = crud.update_grade(db, grade_id, grade)
    if updated is None:
        raise HTTPException(status_code=404, detail="Grade not found")
    return updated
