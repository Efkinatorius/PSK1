package lt.vu.mybatis.model;

public class Subjects {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SUBJECTS.ID
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SUBJECTS.SUBJECT_NAME
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    private String subjectName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SUBJECTS.TEACHER_ID
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    private Integer teacherId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SUBJECTS.ID
     *
     * @return the value of PUBLIC.SUBJECTS.ID
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SUBJECTS.ID
     *
     * @param id the value for PUBLIC.SUBJECTS.ID
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SUBJECTS.SUBJECT_NAME
     *
     * @return the value of PUBLIC.SUBJECTS.SUBJECT_NAME
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SUBJECTS.SUBJECT_NAME
     *
     * @param subjectName the value for PUBLIC.SUBJECTS.SUBJECT_NAME
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SUBJECTS.TEACHER_ID
     *
     * @return the value of PUBLIC.SUBJECTS.TEACHER_ID
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SUBJECTS.TEACHER_ID
     *
     * @param teacherId the value for PUBLIC.SUBJECTS.TEACHER_ID
     *
     * @mbg.generated Mon May 04 13:20:20 EEST 2020
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
    }
}