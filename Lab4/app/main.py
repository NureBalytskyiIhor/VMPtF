from fastapi import FastAPI
from . import models, database
from .routers import students, courses, grades

models.Base.metadata.create_all(bind=database.engine)

app = FastAPI(title="University API")

app.include_router(students.router, prefix="/students", tags=["Students"])
app.include_router(courses.router, prefix="/courses", tags=["Courses"])
app.include_router(grades.router, prefix="/grades", tags=["Grades"])

@app.get("/")
def root():
    return {"message": "University API is running"}
