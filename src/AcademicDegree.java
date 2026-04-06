public enum AcademicDegree {

        BACHELOR("bac"),
        MASTER("master");

        private final String label;

        AcademicDegree(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }


}
